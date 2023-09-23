package edu.uci.ics.amber.engine.architecture.worker

import akka.actor.ActorContext
import edu.uci.ics.amber.engine.architecture.messaginglayer.{
  NetworkInputPort,
  NetworkOutputPort,
  OutputManager
}
import edu.uci.ics.amber.engine.architecture.worker.promisehandlers._
import edu.uci.ics.amber.engine.common.ambermessage.{ControlPayload, DataPayload}
import edu.uci.ics.amber.engine.common.rpc.{
  AsyncRPCClient,
  AsyncRPCHandlerInitializer,
  AsyncRPCServer
}
import edu.uci.ics.amber.engine.common.statetransition.WorkerStateManager
import edu.uci.ics.amber.engine.common.virtualidentity.ActorVirtualIdentity
import edu.uci.ics.amber.engine.common.{AmberLogging, IOperatorExecutor}

class WorkerAsyncRPCHandlerInitializer(
    val actorId: ActorVirtualIdentity,
    val controlInputPort: NetworkInputPort[ControlPayload],
    val dataInputPort: NetworkInputPort[DataPayload],
    val controlOutputPort: NetworkOutputPort[ControlPayload],
    val dataOutputPort: NetworkOutputPort[DataPayload],
    val outputManager: OutputManager,
    val upstreamLinkStatus: UpstreamLinkStatus,
    val pauseManager: PauseManager,
    val dataProcessor: DataProcessor,
    val internalQueue: WorkerInternalQueue,
    var operator: IOperatorExecutor,
    val breakpointManager: BreakpointManager,
    val stateManager: WorkerStateManager,
    val actorContext: ActorContext,
    val epochManager: EpochManager,
    source: AsyncRPCClient,
    receiver: AsyncRPCServer
) extends AsyncRPCHandlerInitializer(source, receiver)
    with AmberLogging
    with OpenOperatorHandler
    with PauseHandler
    with AddPartitioningHandler
    with QueryAndRemoveBreakpointsHandler
    with QueryCurrentInputTupleHandler
    with QueryStatisticsHandler
    with ResumeHandler
    with StartHandler
    with UpdateInputLinkingHandler
    with AssignLocalBreakpointHandler
    with ShutdownDPThreadHandler
    with MonitoringHandler
    with SendImmutableStateHandler
    with AcceptImmutableStateHandler
    with SharePartitionHandler
    with PauseSkewMitigationHandler
    with BackpressureHandler
    with SchedulerTimeSlotEventHandler
    with FlushNetworkBufferHandler
    with WorkerEpochMarkerHandler
    with ModifyOperatorLogicHandler {
  var lastReportTime = 0L
}
