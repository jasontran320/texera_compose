package edu.uci.ics.texera.web.service

import java.util.concurrent.ConcurrentHashMap
import com.typesafe.scalalogging.LazyLogging
import edu.uci.ics.amber.engine.common.AmberUtils
import scala.collection.JavaConverters._
import edu.uci.ics.texera.web.model.websocket.event.{TexeraWebSocketEvent, WorkflowErrorEvent}
import edu.uci.ics.texera.web.{SubscriptionManager, WebsocketInput, WorkflowLifecycleManager}
import edu.uci.ics.texera.web.model.websocket.request.{
  CacheStatusUpdateRequest,
  WorkflowExecuteRequest,
  WorkflowKillRequest
}
import edu.uci.ics.texera.web.resource.WorkflowWebsocketResource
import edu.uci.ics.texera.web.service.WorkflowService.mkWorkflowStateId
import edu.uci.ics.texera.web.storage.WorkflowStateStore
import edu.uci.ics.texera.workflow.common.WorkflowContext
import edu.uci.ics.texera.workflow.common.storage.OpResultStorage
import io.reactivex.rxjava3.disposables.{CompositeDisposable, Disposable}
import io.reactivex.rxjava3.subjects.BehaviorSubject
import org.jooq.types.UInteger
import play.api.libs.json.Json

object WorkflowService {
  private val wIdToWorkflowState = new ConcurrentHashMap[String, WorkflowService]()
  final val userSystemEnabled: Boolean = AmberUtils.amberConfig.getBoolean("user-sys.enabled")
  val cleanUpDeadlineInSeconds: Int =
    AmberUtils.amberConfig.getInt("web-server.workflow-state-cleanup-in-seconds")

  def getAllWorkflowService: Iterable[WorkflowService] = wIdToWorkflowState.values().asScala

  def mkWorkflowStateId(wId: Int): String = {
    wId.toString
  }
  def getOrCreate(
      wId: Int,
      cleanupTimeout: Int = cleanUpDeadlineInSeconds
  ): WorkflowService = {
    wIdToWorkflowState.compute(
      mkWorkflowStateId(wId),
      (_, v) => {
        if (v == null) {
          new WorkflowService(wId, cleanupTimeout)
        } else {
          v
        }
      }
    )
  }
}

class WorkflowService(
    wId: Int,
    cleanUpTimeout: Int
) extends SubscriptionManager
    with LazyLogging {
  // state across execution:
  var opResultStorage: OpResultStorage = new OpResultStorage()
  private val errorSubject = BehaviorSubject.create[TexeraWebSocketEvent]().toSerialized
  val errorHandler: Throwable => Unit = { t =>
    {
      t.printStackTrace()
      errorSubject.onNext(
        WorkflowErrorEvent(generalErrors =
          Map("error" -> (t.getMessage + "\n" + t.getStackTrace.mkString("\n")))
        )
      )
    }
  }
  val wsInput = new WebsocketInput(errorHandler)
  val stateStore = new WorkflowStateStore()
  val resultService: JobResultService =
    new JobResultService(opResultStorage, stateStore)
  val exportService: ResultExportService =
    new ResultExportService(opResultStorage, UInteger.valueOf(wId))
  val operatorCache: WorkflowCacheService =
    new WorkflowCacheService(opResultStorage, stateStore, wsInput)
  var jobService: BehaviorSubject[WorkflowJobService] = BehaviorSubject.create()
  val lifeCycleManager: WorkflowLifecycleManager = new WorkflowLifecycleManager(
    s"wid=$wId",
    cleanUpTimeout,
    () => {
      opResultStorage.close()
      WorkflowService.wIdToWorkflowState.remove(mkWorkflowStateId(wId))
      wsInput.onNext(WorkflowKillRequest(), None)
      unsubscribeAll()
    }
  )

  addSubscription(
    wsInput.subscribe((evt: WorkflowExecuteRequest, uidOpt) => initJobService(evt, uidOpt))
  )

  def connect(onNext: TexeraWebSocketEvent => Unit): Disposable = {
    lifeCycleManager.increaseUserCount()
    val subscriptions = stateStore.getAllStores
      .map(_.getWebsocketEventObservable)
      .map(evtPub =>
        evtPub.subscribe { evts: Iterable[TexeraWebSocketEvent] => evts.foreach(onNext) }
      )
      .toSeq
    val errorSubscription = errorSubject.subscribe { evt: TexeraWebSocketEvent => onNext(evt) }
    new CompositeDisposable(subscriptions :+ errorSubscription: _*)
  }

  def connectToJob(onNext: TexeraWebSocketEvent => Unit): Disposable = {
    var localDisposable = Disposable.empty()
    jobService.subscribe { job: WorkflowJobService =>
      localDisposable.dispose()
      val subscriptions = job.stateStore.getAllStores
        .map(_.getWebsocketEventObservable)
        .map(evtPub =>
          evtPub.subscribe { evts: Iterable[TexeraWebSocketEvent] => evts.foreach(onNext) }
        )
        .toSeq
      localDisposable = new CompositeDisposable(subscriptions: _*)
    }
  }

  def disconnect(): Unit = {
    lifeCycleManager.decreaseUserCount(
      Option(jobService.getValue).map(_.stateStore.jobMetadataStore.getState.state)
    )
  }

  private[this] def createWorkflowContext(
      request: WorkflowExecuteRequest,
      uidOpt: Option[UInteger]
  ): WorkflowContext = {
    val jobID: String = String.valueOf(WorkflowWebsocketResource.nextExecutionID.incrementAndGet)
    if (WorkflowCacheService.isAvailable) {
      operatorCache.updateCacheStatus(
        CacheStatusUpdateRequest(
          request.logicalPlan.operators,
          request.logicalPlan.links,
          request.logicalPlan.breakpoints,
          request.logicalPlan.cachedOperatorIds
        )
      )
    }
    new WorkflowContext(jobID, uidOpt, UInteger.valueOf(wId))
  }

  def initJobService(req: WorkflowExecuteRequest, uidOpt: Option[UInteger]): Unit = {
    if (jobService.getValue != null) {
      //unsubscribe all
      jobService.getValue.unsubscribeAll()
    }

    val job = new WorkflowJobService(
      createWorkflowContext(req, uidOpt),
      wsInput,
      operatorCache,
      resultService,
      req,
      errorHandler,
      convertToJson(req.engineVersion)
    )
    lifeCycleManager.registerCleanUpOnStateChange(job.stateStore)
    jobService.onNext(job)
    job.startWorkflow()
  }

  def convertToJson(frontendVersion: String): String = {
    val environmentVersionMap = Map(
      "engine_version" -> Json.toJson(frontendVersion)
    )
    Json.stringify(Json.toJson(environmentVersionMap))
  }

  override def unsubscribeAll(): Unit = {
    super.unsubscribeAll()
    Option(jobService.getValue).foreach(_.unsubscribeAll())
    operatorCache.unsubscribeAll()
    resultService.unsubscribeAll()
  }

}
