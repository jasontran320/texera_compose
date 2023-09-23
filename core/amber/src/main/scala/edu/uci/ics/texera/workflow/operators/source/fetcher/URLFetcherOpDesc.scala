package edu.uci.ics.texera.workflow.operators.source.fetcher

import com.fasterxml.jackson.annotation.{JsonProperty, JsonPropertyDescription}
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaTitle
import edu.uci.ics.amber.engine.architecture.deploysemantics.layer.OpExecConfig
import edu.uci.ics.texera.workflow.common.metadata.{
  OperatorGroupConstants,
  OperatorInfo,
  OutputPort
}
import edu.uci.ics.texera.workflow.common.operators.source.SourceOperatorDescriptor
import edu.uci.ics.texera.workflow.common.tuple.schema.{AttributeType, OperatorSchemaInfo, Schema}

import scala.collection.immutable.List

class URLFetcherOpDesc extends SourceOperatorDescriptor {

  @JsonProperty(required = true)
  @JsonSchemaTitle("URL")
  @JsonPropertyDescription(
    "Only accepts standard URL format"
  )
  var url: String = _

  @JsonProperty(required = true)
  @JsonSchemaTitle("Decoding")
  @JsonPropertyDescription(
    "The decoding method for the url content"
  )
  var decodingMethod: DecodingMethod = _

  def sourceSchema(): Schema = {
    Schema
      .newBuilder()
      .add(
        "URL content",
        if (decodingMethod == DecodingMethod.UTF_8) { AttributeType.STRING }
        else {
          AttributeType.ANY
        }
      )
      .build()
  }

  override def operatorExecutor(operatorSchemaInfo: OperatorSchemaInfo): OpExecConfig = {
    OpExecConfig
      .oneToOneLayer(
        operatorIdentifier,
        _ =>
          new URLFetcherOpExec(
            url,
            decodingMethod,
            operatorSchemaInfo
          )
      )
      .withNumWorkers(1)
  }

  override def operatorInfo: OperatorInfo =
    OperatorInfo(
      userFriendlyName = "URL fetcher",
      operatorDescription = "Fetch the content of a single url",
      operatorGroupName = OperatorGroupConstants.SOURCE_GROUP,
      inputPorts = List.empty,
      outputPorts = List(OutputPort())
    )

}
