package edu.uci.ics.amber.engine.architecture.scheduling

import edu.uci.ics.amber.engine.architecture.controller.Workflow
import edu.uci.ics.amber.engine.common.virtualidentity.WorkflowIdentity
import edu.uci.ics.amber.engine.e2e.TestOperators
import edu.uci.ics.texera.workflow.common.WorkflowContext
import edu.uci.ics.texera.workflow.common.operators.OperatorDescriptor
import edu.uci.ics.texera.workflow.common.storage.OpResultStorage
import edu.uci.ics.texera.workflow.common.workflow.{
  BreakpointInfo,
  LogicalPlan,
  OperatorLink,
  OperatorPort,
  WorkflowCompiler
}
import edu.uci.ics.texera.workflow.operators.split.SplitOpDesc
import edu.uci.ics.texera.workflow.operators.udf.python.{
  DualInputPortsPythonUDFOpDescV2,
  PythonUDFOpDescV2
}
import org.scalamock.scalatest.MockFactory
import org.scalatest.flatspec.AnyFlatSpec

import scala.jdk.CollectionConverters.asScalaSetConverter

class WorkflowPipelinedRegionsBuilderSpec extends AnyFlatSpec with MockFactory {

  def buildWorkflow(
      operators: List[OperatorDescriptor],
      links: List[OperatorLink]
  ): Workflow = {
    val context = new WorkflowContext
    context.jobId = "workflow-test"

    val texeraWorkflowCompiler = new WorkflowCompiler(
      LogicalPlan(operators, links, List[BreakpointInfo]()),
      context
    )
    texeraWorkflowCompiler.amberWorkflow(WorkflowIdentity("workflow-test"), new OpResultStorage())
  }

  "Pipelined Regions" should "correctly find regions in headerlessCsv->keyword->sink workflow" in {
    val headerlessCsvOpDesc = TestOperators.headerlessSmallCsvScanOpDesc()
    val keywordOpDesc = TestOperators.keywordSearchOpDesc("column-1", "Asia")
    val sink = TestOperators.sinkOpDesc()
    val workflow = buildWorkflow(
      List(headerlessCsvOpDesc, keywordOpDesc, sink),
      List(
        OperatorLink(
          OperatorPort(headerlessCsvOpDesc.operatorID, 0),
          OperatorPort(keywordOpDesc.operatorID, 0)
        ),
        OperatorLink(OperatorPort(keywordOpDesc.operatorID, 0), OperatorPort(sink.operatorID, 0))
      )
    )

    val pipelinedRegions = workflow.physicalPlan.pipelinedRegionsDAG
    assert(pipelinedRegions.vertexSet().size == 1)
  }

  "Pipelined Regions" should "correctly find regions in csv->(csv->)->join->sink workflow" in {
    val headerlessCsvOpDesc1 = TestOperators.headerlessSmallCsvScanOpDesc()
    val headerlessCsvOpDesc2 = TestOperators.headerlessSmallCsvScanOpDesc()
    val joinOpDesc = TestOperators.joinOpDesc("column-1", "column-1")
    val sink = TestOperators.sinkOpDesc()
    val workflow = buildWorkflow(
      List(
        headerlessCsvOpDesc1,
        headerlessCsvOpDesc2,
        joinOpDesc,
        sink
      ),
      List(
        OperatorLink(
          OperatorPort(headerlessCsvOpDesc1.operatorID, 0),
          OperatorPort(joinOpDesc.operatorID, 0)
        ),
        OperatorLink(
          OperatorPort(headerlessCsvOpDesc2.operatorID, 0),
          OperatorPort(joinOpDesc.operatorID, 1)
        ),
        OperatorLink(
          OperatorPort(joinOpDesc.operatorID, 0),
          OperatorPort(sink.operatorID, 0)
        )
      )
    )

    val pipelinedRegions = workflow.physicalPlan.pipelinedRegionsDAG
    assert(pipelinedRegions.vertexSet().size == 2)

    val buildRegion = pipelinedRegions
      .vertexSet()
      .asScala
      .find(v => v.operators.toList.exists(op => op.operator == headerlessCsvOpDesc1.operatorID))
      .get
    val probeRegion = pipelinedRegions
      .vertexSet()
      .asScala
      .find(v => v.operators.toList.exists(op => op.operator == headerlessCsvOpDesc2.operatorID))
      .get

    assert(pipelinedRegions.getAncestors(probeRegion).size() == 1)
    assert(pipelinedRegions.getAncestors(probeRegion).contains(buildRegion))
    assert(buildRegion.blockingDownstreamOperatorsInOtherRegions.length == 1)
    assert(
      buildRegion.blockingDownstreamOperatorsInOtherRegions.exists(op =>
        op.operator == joinOpDesc.operatorID
      )
    )
  }

  "Pipelined Regions" should "correctly find regions in csv->->filter->join->sink workflow" in {
    val headerlessCsvOpDesc1 = TestOperators.headerlessSmallCsvScanOpDesc()
    val keywordOpDesc = TestOperators.keywordSearchOpDesc("column-1", "Asia")
    val joinOpDesc = TestOperators.joinOpDesc("column-1", "column-1")
    val sink = TestOperators.sinkOpDesc()
    val workflow = buildWorkflow(
      List(
        headerlessCsvOpDesc1,
        keywordOpDesc,
        joinOpDesc,
        sink
      ),
      List(
        OperatorLink(
          OperatorPort(headerlessCsvOpDesc1.operatorID, 0),
          OperatorPort(joinOpDesc.operatorID, 0)
        ),
        OperatorLink(
          OperatorPort(headerlessCsvOpDesc1.operatorID, 0),
          OperatorPort(keywordOpDesc.operatorID, 0)
        ),
        OperatorLink(
          OperatorPort(keywordOpDesc.operatorID, 0),
          OperatorPort(joinOpDesc.operatorID, 1)
        ),
        OperatorLink(
          OperatorPort(joinOpDesc.operatorID, 0),
          OperatorPort(sink.operatorID, 0)
        )
      )
    )
    val pipelinedRegions = workflow.physicalPlan.pipelinedRegionsDAG
    assert(pipelinedRegions.vertexSet().size == 2)
  }

  "Pipelined Regions" should "correctly find regions in buildcsv->probecsv->hashjoin->hashjoin->sink workflow" in {
    val buildCsv = TestOperators.headerlessSmallCsvScanOpDesc()
    val probeCsv = TestOperators.smallCsvScanOpDesc()
    val hashJoin1 = TestOperators.joinOpDesc("column-1", "Region")
    val hashJoin2 = TestOperators.joinOpDesc("column-2", "Country")
    val sink = TestOperators.sinkOpDesc()
    val workflow = buildWorkflow(
      List(
        buildCsv,
        probeCsv,
        hashJoin1,
        hashJoin2,
        sink
      ),
      List(
        OperatorLink(
          OperatorPort(buildCsv.operatorID, 0),
          OperatorPort(hashJoin1.operatorID, 0)
        ),
        OperatorLink(
          OperatorPort(probeCsv.operatorID, 0),
          OperatorPort(hashJoin1.operatorID, 1)
        ),
        OperatorLink(
          OperatorPort(buildCsv.operatorID, 0),
          OperatorPort(hashJoin2.operatorID, 0)
        ),
        OperatorLink(
          OperatorPort(hashJoin1.operatorID, 0),
          OperatorPort(hashJoin2.operatorID, 1)
        ),
        OperatorLink(
          OperatorPort(hashJoin2.operatorID, 0),
          OperatorPort(sink.operatorID, 0)
        )
      )
    )
    val pipelinedRegions = workflow.physicalPlan.pipelinedRegionsDAG
    assert(pipelinedRegions.vertexSet().size == 2)
  }

  "Pipelined Regions" should "correctly find regions in csv->split->training-infer workflow" in {
    val csv = TestOperators.headerlessSmallCsvScanOpDesc()
    val split = new SplitOpDesc()
    val training = new PythonUDFOpDescV2()
    val inference = new DualInputPortsPythonUDFOpDescV2()
    val sink = TestOperators.sinkOpDesc()
    val workflow = buildWorkflow(
      List(
        csv,
        split,
        training,
        inference,
        sink
      ),
      List(
        OperatorLink(
          OperatorPort(csv.operatorID, 0),
          OperatorPort(split.operatorID, 0)
        ),
        OperatorLink(
          OperatorPort(split.operatorID, 0),
          OperatorPort(training.operatorID, 0)
        ),
        OperatorLink(
          OperatorPort(training.operatorID, 0),
          OperatorPort(inference.operatorID, 0)
        ),
        OperatorLink(
          OperatorPort(split.operatorID, 1),
          OperatorPort(inference.operatorID, 1)
        ),
        OperatorLink(
          OperatorPort(inference.operatorID, 0),
          OperatorPort(sink.operatorID, 0)
        )
      )
    )
    val pipelinedRegions = workflow.physicalPlan.pipelinedRegionsDAG
    assert(pipelinedRegions.vertexSet().size == 2)
  }

}
