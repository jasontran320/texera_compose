// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package edu.uci.ics.texera.web.workflowresultstate

object WorkflowresultstateProto extends _root_.scalapb.GeneratedFileObject {
  lazy val dependencies: Seq[_root_.scalapb.GeneratedFileObject] = Seq(
    scalapb.options.ScalapbProto
  )
  lazy val messagesCompanions: Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]] =
    Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]](
      edu.uci.ics.texera.web.workflowresultstate.WorkflowResultStore,
      edu.uci.ics.texera.web.workflowresultstate.OperatorResultMetadata
    )
  private lazy val ProtoBytes: _root_.scala.Array[Byte] =
      scalapb.Encoding.fromBase64(scala.collection.immutable.Seq(
  """CixlZHUvdWNpL2ljcy90ZXhlcmEvd29ya2Zsb3dyZXN1bHRzdGF0ZS5wcm90bxIWZWR1LnVjaS5pY3MudGV4ZXJhLndlYhoVc
  2NhbGFwYi9zY2FsYXBiLnByb3RvIpQCChNXb3JrZmxvd1Jlc3VsdFN0b3JlEnUKDW9wZXJhdG9yX2luZm8YASADKAsyPS5lZHUud
  WNpLmljcy50ZXhlcmEud2ViLldvcmtmbG93UmVzdWx0U3RvcmUuT3BlcmF0b3JJbmZvRW50cnlCEeI/DhIMb3BlcmF0b3JJbmZvU
  gxvcGVyYXRvckluZm8ahQEKEU9wZXJhdG9ySW5mb0VudHJ5EhoKA2tleRgBIAEoCUII4j8FEgNrZXlSA2tleRJQCgV2YWx1ZRgCI
  AEoCzIuLmVkdS51Y2kuaWNzLnRleGVyYS53ZWIuT3BlcmF0b3JSZXN1bHRNZXRhZGF0YUIK4j8HEgV2YWx1ZVIFdmFsdWU6AjgBI
  ogBChZPcGVyYXRvclJlc3VsdE1ldGFkYXRhEjAKC3R1cGxlX2NvdW50GAEgASgFQg/iPwwSCnR1cGxlQ291bnRSCnR1cGxlQ291b
  nQSPAoPY2hhbmdlX2RldGVjdG9yGAIgASgJQhPiPxASDmNoYW5nZURldGVjdG9yUg5jaGFuZ2VEZXRlY3RvckIJ4j8GSABYAHgAY
  gZwcm90bzM="""
      ).mkString)
  lazy val scalaDescriptor: _root_.scalapb.descriptors.FileDescriptor = {
    val scalaProto = com.google.protobuf.descriptor.FileDescriptorProto.parseFrom(ProtoBytes)
    _root_.scalapb.descriptors.FileDescriptor.buildFrom(scalaProto, dependencies.map(_.scalaDescriptor))
  }
  lazy val javaDescriptor: com.google.protobuf.Descriptors.FileDescriptor = {
    val javaProto = com.google.protobuf.DescriptorProtos.FileDescriptorProto.parseFrom(ProtoBytes)
    com.google.protobuf.Descriptors.FileDescriptor.buildFrom(javaProto, _root_.scala.Array(
      scalapb.options.ScalapbProto.javaDescriptor
    ))
  }
  @deprecated("Use javaDescriptor instead. In a future version this will refer to scalaDescriptor.", "ScalaPB 0.5.47")
  def descriptor: com.google.protobuf.Descriptors.FileDescriptor = javaDescriptor
}