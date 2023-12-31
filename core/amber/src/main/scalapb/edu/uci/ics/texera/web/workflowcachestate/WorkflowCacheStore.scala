// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package edu.uci.ics.texera.web.workflowcachestate

@SerialVersionUID(0L)
final case class WorkflowCacheStore(
    operatorInfo: _root_.scala.collection.immutable.Map[_root_.scala.Predef.String, edu.uci.ics.texera.web.workflowcachestate.CacheState] = _root_.scala.collection.immutable.Map.empty
    ) extends scalapb.GeneratedMessage with scalapb.lenses.Updatable[WorkflowCacheStore] {
    @transient
    private[this] var __serializedSizeCachedValue: _root_.scala.Int = 0
    private[this] def __computeSerializedValue(): _root_.scala.Int = {
      var __size = 0
      operatorInfo.foreach { __item =>
        val __value = edu.uci.ics.texera.web.workflowcachestate.WorkflowCacheStore._typemapper_operatorInfo.toBase(__item)
        __size += 1 + _root_.com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(__value.serializedSize) + __value.serializedSize
      }
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
      operatorInfo.foreach { __v =>
        val __m = edu.uci.ics.texera.web.workflowcachestate.WorkflowCacheStore._typemapper_operatorInfo.toBase(__v)
        _output__.writeTag(1, 2)
        _output__.writeUInt32NoTag(__m.serializedSize)
        __m.writeTo(_output__)
      };
    }
    def clearOperatorInfo = copy(operatorInfo = _root_.scala.collection.immutable.Map.empty)
    def addOperatorInfo(__vs: (_root_.scala.Predef.String, edu.uci.ics.texera.web.workflowcachestate.CacheState)*): WorkflowCacheStore = addAllOperatorInfo(__vs)
    def addAllOperatorInfo(__vs: Iterable[(_root_.scala.Predef.String, edu.uci.ics.texera.web.workflowcachestate.CacheState)]): WorkflowCacheStore = copy(operatorInfo = operatorInfo ++ __vs)
    def withOperatorInfo(__v: _root_.scala.collection.immutable.Map[_root_.scala.Predef.String, edu.uci.ics.texera.web.workflowcachestate.CacheState]): WorkflowCacheStore = copy(operatorInfo = __v)
    def getFieldByNumber(__fieldNumber: _root_.scala.Int): _root_.scala.Any = {
      (__fieldNumber: @_root_.scala.unchecked) match {
        case 1 => operatorInfo.iterator.map(edu.uci.ics.texera.web.workflowcachestate.WorkflowCacheStore._typemapper_operatorInfo.toBase(_)).toSeq
      }
    }
    def getField(__field: _root_.scalapb.descriptors.FieldDescriptor): _root_.scalapb.descriptors.PValue = {
      _root_.scala.Predef.require(__field.containingMessage eq companion.scalaDescriptor)
      (__field.number: @_root_.scala.unchecked) match {
        case 1 => _root_.scalapb.descriptors.PRepeated(operatorInfo.iterator.map(edu.uci.ics.texera.web.workflowcachestate.WorkflowCacheStore._typemapper_operatorInfo.toBase(_).toPMessage).toVector)
      }
    }
    def toProtoString: _root_.scala.Predef.String = _root_.scalapb.TextFormat.printToSingleLineUnicodeString(this)
    def companion = edu.uci.ics.texera.web.workflowcachestate.WorkflowCacheStore
    // @@protoc_insertion_point(GeneratedMessage[edu.uci.ics.texera.web.WorkflowCacheStore])
}

object WorkflowCacheStore extends scalapb.GeneratedMessageCompanion[edu.uci.ics.texera.web.workflowcachestate.WorkflowCacheStore] {
  implicit def messageCompanion: scalapb.GeneratedMessageCompanion[edu.uci.ics.texera.web.workflowcachestate.WorkflowCacheStore] = this
  def parseFrom(`_input__`: _root_.com.google.protobuf.CodedInputStream): edu.uci.ics.texera.web.workflowcachestate.WorkflowCacheStore = {
    val __operatorInfo: _root_.scala.collection.mutable.Builder[(_root_.scala.Predef.String, edu.uci.ics.texera.web.workflowcachestate.CacheState), _root_.scala.collection.immutable.Map[_root_.scala.Predef.String, edu.uci.ics.texera.web.workflowcachestate.CacheState]] = _root_.scala.collection.immutable.Map.newBuilder[_root_.scala.Predef.String, edu.uci.ics.texera.web.workflowcachestate.CacheState]
    var _done__ = false
    while (!_done__) {
      val _tag__ = _input__.readTag()
      _tag__ match {
        case 0 => _done__ = true
        case 10 =>
          __operatorInfo += edu.uci.ics.texera.web.workflowcachestate.WorkflowCacheStore._typemapper_operatorInfo.toCustom(_root_.scalapb.LiteParser.readMessage[edu.uci.ics.texera.web.workflowcachestate.WorkflowCacheStore.OperatorInfoEntry](_input__))
        case tag => _input__.skipField(tag)
      }
    }
    edu.uci.ics.texera.web.workflowcachestate.WorkflowCacheStore(
        operatorInfo = __operatorInfo.result()
    )
  }
  implicit def messageReads: _root_.scalapb.descriptors.Reads[edu.uci.ics.texera.web.workflowcachestate.WorkflowCacheStore] = _root_.scalapb.descriptors.Reads{
    case _root_.scalapb.descriptors.PMessage(__fieldsMap) =>
      _root_.scala.Predef.require(__fieldsMap.keys.forall(_.containingMessage eq scalaDescriptor), "FieldDescriptor does not match message type.")
      edu.uci.ics.texera.web.workflowcachestate.WorkflowCacheStore(
        operatorInfo = __fieldsMap.get(scalaDescriptor.findFieldByNumber(1).get).map(_.as[_root_.scala.Seq[edu.uci.ics.texera.web.workflowcachestate.WorkflowCacheStore.OperatorInfoEntry]]).getOrElse(_root_.scala.Seq.empty).iterator.map(edu.uci.ics.texera.web.workflowcachestate.WorkflowCacheStore._typemapper_operatorInfo.toCustom(_)).toMap
      )
    case _ => throw new RuntimeException("Expected PMessage")
  }
  def javaDescriptor: _root_.com.google.protobuf.Descriptors.Descriptor = WorkflowcachestateProto.javaDescriptor.getMessageTypes().get(0)
  def scalaDescriptor: _root_.scalapb.descriptors.Descriptor = WorkflowcachestateProto.scalaDescriptor.messages(0)
  def messageCompanionForFieldNumber(__number: _root_.scala.Int): _root_.scalapb.GeneratedMessageCompanion[_] = {
    var __out: _root_.scalapb.GeneratedMessageCompanion[_] = null
    (__number: @_root_.scala.unchecked) match {
      case 1 => __out = edu.uci.ics.texera.web.workflowcachestate.WorkflowCacheStore.OperatorInfoEntry
    }
    __out
  }
  lazy val nestedMessagesCompanions: Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]] =
    Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]](
      _root_.edu.uci.ics.texera.web.workflowcachestate.WorkflowCacheStore.OperatorInfoEntry
    )
  def enumCompanionForFieldNumber(__fieldNumber: _root_.scala.Int): _root_.scalapb.GeneratedEnumCompanion[_] = throw new MatchError(__fieldNumber)
  lazy val defaultInstance = edu.uci.ics.texera.web.workflowcachestate.WorkflowCacheStore(
    operatorInfo = _root_.scala.collection.immutable.Map.empty
  )
  @SerialVersionUID(0L)
  final case class OperatorInfoEntry(
      key: _root_.scala.Predef.String = "",
      value: edu.uci.ics.texera.web.workflowcachestate.CacheState = edu.uci.ics.texera.web.workflowcachestate.CacheState.VALID
      ) extends scalapb.GeneratedMessage with scalapb.lenses.Updatable[OperatorInfoEntry] {
      @transient
      private[this] var __serializedSizeCachedValue: _root_.scala.Int = 0
      private[this] def __computeSerializedValue(): _root_.scala.Int = {
        var __size = 0
        
        {
          val __value = key
          if (!__value.isEmpty) {
            __size += _root_.com.google.protobuf.CodedOutputStream.computeStringSize(1, __value)
          }
        };
        
        {
          val __value = value.value
          if (__value != 0) {
            __size += _root_.com.google.protobuf.CodedOutputStream.computeEnumSize(2, __value)
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
          val __v = key
          if (!__v.isEmpty) {
            _output__.writeString(1, __v)
          }
        };
        {
          val __v = value.value
          if (__v != 0) {
            _output__.writeEnum(2, __v)
          }
        };
      }
      def withKey(__v: _root_.scala.Predef.String): OperatorInfoEntry = copy(key = __v)
      def withValue(__v: edu.uci.ics.texera.web.workflowcachestate.CacheState): OperatorInfoEntry = copy(value = __v)
      def getFieldByNumber(__fieldNumber: _root_.scala.Int): _root_.scala.Any = {
        (__fieldNumber: @_root_.scala.unchecked) match {
          case 1 => {
            val __t = key
            if (__t != "") __t else null
          }
          case 2 => {
            val __t = value.javaValueDescriptor
            if (__t.getNumber() != 0) __t else null
          }
        }
      }
      def getField(__field: _root_.scalapb.descriptors.FieldDescriptor): _root_.scalapb.descriptors.PValue = {
        _root_.scala.Predef.require(__field.containingMessage eq companion.scalaDescriptor)
        (__field.number: @_root_.scala.unchecked) match {
          case 1 => _root_.scalapb.descriptors.PString(key)
          case 2 => _root_.scalapb.descriptors.PEnum(value.scalaValueDescriptor)
        }
      }
      def toProtoString: _root_.scala.Predef.String = _root_.scalapb.TextFormat.printToSingleLineUnicodeString(this)
      def companion = edu.uci.ics.texera.web.workflowcachestate.WorkflowCacheStore.OperatorInfoEntry
      // @@protoc_insertion_point(GeneratedMessage[edu.uci.ics.texera.web.WorkflowCacheStore.OperatorInfoEntry])
  }
  
  object OperatorInfoEntry extends scalapb.GeneratedMessageCompanion[edu.uci.ics.texera.web.workflowcachestate.WorkflowCacheStore.OperatorInfoEntry] {
    implicit def messageCompanion: scalapb.GeneratedMessageCompanion[edu.uci.ics.texera.web.workflowcachestate.WorkflowCacheStore.OperatorInfoEntry] = this
    def parseFrom(`_input__`: _root_.com.google.protobuf.CodedInputStream): edu.uci.ics.texera.web.workflowcachestate.WorkflowCacheStore.OperatorInfoEntry = {
      var __key: _root_.scala.Predef.String = ""
      var __value: edu.uci.ics.texera.web.workflowcachestate.CacheState = edu.uci.ics.texera.web.workflowcachestate.CacheState.VALID
      var _done__ = false
      while (!_done__) {
        val _tag__ = _input__.readTag()
        _tag__ match {
          case 0 => _done__ = true
          case 10 =>
            __key = _input__.readStringRequireUtf8()
          case 16 =>
            __value = edu.uci.ics.texera.web.workflowcachestate.CacheState.fromValue(_input__.readEnum())
          case tag => _input__.skipField(tag)
        }
      }
      edu.uci.ics.texera.web.workflowcachestate.WorkflowCacheStore.OperatorInfoEntry(
          key = __key,
          value = __value
      )
    }
    implicit def messageReads: _root_.scalapb.descriptors.Reads[edu.uci.ics.texera.web.workflowcachestate.WorkflowCacheStore.OperatorInfoEntry] = _root_.scalapb.descriptors.Reads{
      case _root_.scalapb.descriptors.PMessage(__fieldsMap) =>
        _root_.scala.Predef.require(__fieldsMap.keys.forall(_.containingMessage eq scalaDescriptor), "FieldDescriptor does not match message type.")
        edu.uci.ics.texera.web.workflowcachestate.WorkflowCacheStore.OperatorInfoEntry(
          key = __fieldsMap.get(scalaDescriptor.findFieldByNumber(1).get).map(_.as[_root_.scala.Predef.String]).getOrElse(""),
          value = edu.uci.ics.texera.web.workflowcachestate.CacheState.fromValue(__fieldsMap.get(scalaDescriptor.findFieldByNumber(2).get).map(_.as[_root_.scalapb.descriptors.EnumValueDescriptor]).getOrElse(edu.uci.ics.texera.web.workflowcachestate.CacheState.VALID.scalaValueDescriptor).number)
        )
      case _ => throw new RuntimeException("Expected PMessage")
    }
    def javaDescriptor: _root_.com.google.protobuf.Descriptors.Descriptor = edu.uci.ics.texera.web.workflowcachestate.WorkflowCacheStore.javaDescriptor.getNestedTypes().get(0)
    def scalaDescriptor: _root_.scalapb.descriptors.Descriptor = edu.uci.ics.texera.web.workflowcachestate.WorkflowCacheStore.scalaDescriptor.nestedMessages(0)
    def messageCompanionForFieldNumber(__number: _root_.scala.Int): _root_.scalapb.GeneratedMessageCompanion[_] = throw new MatchError(__number)
    lazy val nestedMessagesCompanions: Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]] = Seq.empty
    def enumCompanionForFieldNumber(__fieldNumber: _root_.scala.Int): _root_.scalapb.GeneratedEnumCompanion[_] = {
      (__fieldNumber: @_root_.scala.unchecked) match {
        case 2 => edu.uci.ics.texera.web.workflowcachestate.CacheState
      }
    }
    lazy val defaultInstance = edu.uci.ics.texera.web.workflowcachestate.WorkflowCacheStore.OperatorInfoEntry(
      key = "",
      value = edu.uci.ics.texera.web.workflowcachestate.CacheState.VALID
    )
    implicit class OperatorInfoEntryLens[UpperPB](_l: _root_.scalapb.lenses.Lens[UpperPB, edu.uci.ics.texera.web.workflowcachestate.WorkflowCacheStore.OperatorInfoEntry]) extends _root_.scalapb.lenses.ObjectLens[UpperPB, edu.uci.ics.texera.web.workflowcachestate.WorkflowCacheStore.OperatorInfoEntry](_l) {
      def key: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Predef.String] = field(_.key)((c_, f_) => c_.copy(key = f_))
      def value: _root_.scalapb.lenses.Lens[UpperPB, edu.uci.ics.texera.web.workflowcachestate.CacheState] = field(_.value)((c_, f_) => c_.copy(value = f_))
    }
    final val KEY_FIELD_NUMBER = 1
    final val VALUE_FIELD_NUMBER = 2
    @transient
    implicit val keyValueMapper: _root_.scalapb.TypeMapper[edu.uci.ics.texera.web.workflowcachestate.WorkflowCacheStore.OperatorInfoEntry, (_root_.scala.Predef.String, edu.uci.ics.texera.web.workflowcachestate.CacheState)] =
      _root_.scalapb.TypeMapper[edu.uci.ics.texera.web.workflowcachestate.WorkflowCacheStore.OperatorInfoEntry, (_root_.scala.Predef.String, edu.uci.ics.texera.web.workflowcachestate.CacheState)](__m => (__m.key, __m.value))(__p => edu.uci.ics.texera.web.workflowcachestate.WorkflowCacheStore.OperatorInfoEntry(__p._1, __p._2))
    def of(
      key: _root_.scala.Predef.String,
      value: edu.uci.ics.texera.web.workflowcachestate.CacheState
    ): _root_.edu.uci.ics.texera.web.workflowcachestate.WorkflowCacheStore.OperatorInfoEntry = _root_.edu.uci.ics.texera.web.workflowcachestate.WorkflowCacheStore.OperatorInfoEntry(
      key,
      value
    )
    // @@protoc_insertion_point(GeneratedMessageCompanion[edu.uci.ics.texera.web.WorkflowCacheStore.OperatorInfoEntry])
  }
  
  implicit class WorkflowCacheStoreLens[UpperPB](_l: _root_.scalapb.lenses.Lens[UpperPB, edu.uci.ics.texera.web.workflowcachestate.WorkflowCacheStore]) extends _root_.scalapb.lenses.ObjectLens[UpperPB, edu.uci.ics.texera.web.workflowcachestate.WorkflowCacheStore](_l) {
    def operatorInfo: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.collection.immutable.Map[_root_.scala.Predef.String, edu.uci.ics.texera.web.workflowcachestate.CacheState]] = field(_.operatorInfo)((c_, f_) => c_.copy(operatorInfo = f_))
  }
  final val OPERATOR_INFO_FIELD_NUMBER = 1
  @transient
  private[workflowcachestate] val _typemapper_operatorInfo: _root_.scalapb.TypeMapper[edu.uci.ics.texera.web.workflowcachestate.WorkflowCacheStore.OperatorInfoEntry, (_root_.scala.Predef.String, edu.uci.ics.texera.web.workflowcachestate.CacheState)] = implicitly[_root_.scalapb.TypeMapper[edu.uci.ics.texera.web.workflowcachestate.WorkflowCacheStore.OperatorInfoEntry, (_root_.scala.Predef.String, edu.uci.ics.texera.web.workflowcachestate.CacheState)]]
  def of(
    operatorInfo: _root_.scala.collection.immutable.Map[_root_.scala.Predef.String, edu.uci.ics.texera.web.workflowcachestate.CacheState]
  ): _root_.edu.uci.ics.texera.web.workflowcachestate.WorkflowCacheStore = _root_.edu.uci.ics.texera.web.workflowcachestate.WorkflowCacheStore(
    operatorInfo
  )
  // @@protoc_insertion_point(GeneratedMessageCompanion[edu.uci.ics.texera.web.WorkflowCacheStore])
}
