// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package edu.uci.ics.amber.engine.common.virtualidentity

/** final case class LayerIdentity (
  *    workflow: String,
  *    operator: String,
  *    layerID: String
  * )
  */
@SerialVersionUID(0L)
final case class LayerIdentity(
    workflow: _root_.scala.Predef.String,
    operator: _root_.scala.Predef.String,
    layerID: _root_.scala.Predef.String
    ) extends scalapb.GeneratedMessage with scalapb.lenses.Updatable[LayerIdentity] {
    @transient
    private[this] var __serializedSizeCachedValue: _root_.scala.Int = 0
    private[this] def __computeSerializedValue(): _root_.scala.Int = {
      var __size = 0
      
      {
        val __value = workflow
        if (!__value.isEmpty) {
          __size += _root_.com.google.protobuf.CodedOutputStream.computeStringSize(1, __value)
        }
      };
      
      {
        val __value = operator
        if (!__value.isEmpty) {
          __size += _root_.com.google.protobuf.CodedOutputStream.computeStringSize(2, __value)
        }
      };
      
      {
        val __value = layerID
        if (!__value.isEmpty) {
          __size += _root_.com.google.protobuf.CodedOutputStream.computeStringSize(3, __value)
        }
      };
      __size
    }
    override def serializedSize: _root_.scala.Int = {
      var read = __serializedSizeCachedValue
      if (read == 0) {
        read = __computeSerializedValue()
        __serializedSizeCachedValue = read
      }
      read
    }
    def writeTo(`_output__`: _root_.com.google.protobuf.CodedOutputStream): _root_.scala.Unit = {
      {
        val __v = workflow
        if (!__v.isEmpty) {
          _output__.writeString(1, __v)
        }
      };
      {
        val __v = operator
        if (!__v.isEmpty) {
          _output__.writeString(2, __v)
        }
      };
      {
        val __v = layerID
        if (!__v.isEmpty) {
          _output__.writeString(3, __v)
        }
      };
    }
    def withWorkflow(__v: _root_.scala.Predef.String): LayerIdentity = copy(workflow = __v)
    def withOperator(__v: _root_.scala.Predef.String): LayerIdentity = copy(operator = __v)
    def withLayerID(__v: _root_.scala.Predef.String): LayerIdentity = copy(layerID = __v)
    def getFieldByNumber(__fieldNumber: _root_.scala.Int): _root_.scala.Any = {
      (__fieldNumber: @_root_.scala.unchecked) match {
        case 1 => {
          val __t = workflow
          if (__t != "") __t else null
        }
        case 2 => {
          val __t = operator
          if (__t != "") __t else null
        }
        case 3 => {
          val __t = layerID
          if (__t != "") __t else null
        }
      }
    }
    def getField(__field: _root_.scalapb.descriptors.FieldDescriptor): _root_.scalapb.descriptors.PValue = {
      _root_.scala.Predef.require(__field.containingMessage eq companion.scalaDescriptor)
      (__field.number: @_root_.scala.unchecked) match {
        case 1 => _root_.scalapb.descriptors.PString(workflow)
        case 2 => _root_.scalapb.descriptors.PString(operator)
        case 3 => _root_.scalapb.descriptors.PString(layerID)
      }
    }
    def toProtoString: _root_.scala.Predef.String = _root_.scalapb.TextFormat.printToSingleLineUnicodeString(this)
    def companion = edu.uci.ics.amber.engine.common.virtualidentity.LayerIdentity
    // @@protoc_insertion_point(GeneratedMessage[edu.uci.ics.amber.engine.common.LayerIdentity])
}

object LayerIdentity extends scalapb.GeneratedMessageCompanion[edu.uci.ics.amber.engine.common.virtualidentity.LayerIdentity] {
  implicit def messageCompanion: scalapb.GeneratedMessageCompanion[edu.uci.ics.amber.engine.common.virtualidentity.LayerIdentity] = this
  def parseFrom(`_input__`: _root_.com.google.protobuf.CodedInputStream): edu.uci.ics.amber.engine.common.virtualidentity.LayerIdentity = {
    var __workflow: _root_.scala.Predef.String = ""
    var __operator: _root_.scala.Predef.String = ""
    var __layerID: _root_.scala.Predef.String = ""
    var _done__ = false
    while (!_done__) {
      val _tag__ = _input__.readTag()
      _tag__ match {
        case 0 => _done__ = true
        case 10 =>
          __workflow = _input__.readStringRequireUtf8()
        case 18 =>
          __operator = _input__.readStringRequireUtf8()
        case 26 =>
          __layerID = _input__.readStringRequireUtf8()
        case tag => _input__.skipField(tag)
      }
    }
    edu.uci.ics.amber.engine.common.virtualidentity.LayerIdentity(
        workflow = __workflow,
        operator = __operator,
        layerID = __layerID
    )
  }
  implicit def messageReads: _root_.scalapb.descriptors.Reads[edu.uci.ics.amber.engine.common.virtualidentity.LayerIdentity] = _root_.scalapb.descriptors.Reads{
    case _root_.scalapb.descriptors.PMessage(__fieldsMap) =>
      _root_.scala.Predef.require(__fieldsMap.keys.forall(_.containingMessage eq scalaDescriptor), "FieldDescriptor does not match message type.")
      edu.uci.ics.amber.engine.common.virtualidentity.LayerIdentity(
        workflow = __fieldsMap.get(scalaDescriptor.findFieldByNumber(1).get).map(_.as[_root_.scala.Predef.String]).getOrElse(""),
        operator = __fieldsMap.get(scalaDescriptor.findFieldByNumber(2).get).map(_.as[_root_.scala.Predef.String]).getOrElse(""),
        layerID = __fieldsMap.get(scalaDescriptor.findFieldByNumber(3).get).map(_.as[_root_.scala.Predef.String]).getOrElse("")
      )
    case _ => throw new RuntimeException("Expected PMessage")
  }
  def javaDescriptor: _root_.com.google.protobuf.Descriptors.Descriptor = VirtualidentityProto.javaDescriptor.getMessageTypes().get(1)
  def scalaDescriptor: _root_.scalapb.descriptors.Descriptor = VirtualidentityProto.scalaDescriptor.messages(1)
  def messageCompanionForFieldNumber(__number: _root_.scala.Int): _root_.scalapb.GeneratedMessageCompanion[_] = throw new MatchError(__number)
  lazy val nestedMessagesCompanions: Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]] = Seq.empty
  def enumCompanionForFieldNumber(__fieldNumber: _root_.scala.Int): _root_.scalapb.GeneratedEnumCompanion[_] = throw new MatchError(__fieldNumber)
  lazy val defaultInstance = edu.uci.ics.amber.engine.common.virtualidentity.LayerIdentity(
    workflow = "",
    operator = "",
    layerID = ""
  )
  implicit class LayerIdentityLens[UpperPB](_l: _root_.scalapb.lenses.Lens[UpperPB, edu.uci.ics.amber.engine.common.virtualidentity.LayerIdentity]) extends _root_.scalapb.lenses.ObjectLens[UpperPB, edu.uci.ics.amber.engine.common.virtualidentity.LayerIdentity](_l) {
    def workflow: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Predef.String] = field(_.workflow)((c_, f_) => c_.copy(workflow = f_))
    def operator: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Predef.String] = field(_.operator)((c_, f_) => c_.copy(operator = f_))
    def layerID: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Predef.String] = field(_.layerID)((c_, f_) => c_.copy(layerID = f_))
  }
  final val WORKFLOW_FIELD_NUMBER = 1
  final val OPERATOR_FIELD_NUMBER = 2
  final val LAYERID_FIELD_NUMBER = 3
  def of(
    workflow: _root_.scala.Predef.String,
    operator: _root_.scala.Predef.String,
    layerID: _root_.scala.Predef.String
  ): _root_.edu.uci.ics.amber.engine.common.virtualidentity.LayerIdentity = _root_.edu.uci.ics.amber.engine.common.virtualidentity.LayerIdentity(
    workflow,
    operator,
    layerID
  )
  // @@protoc_insertion_point(GeneratedMessageCompanion[edu.uci.ics.amber.engine.common.LayerIdentity])
}
