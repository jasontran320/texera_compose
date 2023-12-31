// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package edu.uci.ics.amber.engine.architecture.worker.workloadmetrics

@SerialVersionUID(0L)
final case class Loads(
    worker: edu.uci.ics.amber.engine.common.virtualidentity.ActorVirtualIdentity,
    load: _root_.scala.Seq[_root_.scala.Long]
    ) extends scalapb.GeneratedMessage with scalapb.lenses.Updatable[Loads] {
    private[this] def loadSerializedSize = {
      if (__loadSerializedSizeField == 0) __loadSerializedSizeField = {
        var __s: _root_.scala.Int = 0
        load.foreach(__i => __s += _root_.com.google.protobuf.CodedOutputStream.computeInt64SizeNoTag(__i))
        __s
      }
      __loadSerializedSizeField
    }
    @transient private[this] var __loadSerializedSizeField: _root_.scala.Int = 0
    @transient
    private[this] var __serializedSizeCachedValue: _root_.scala.Int = 0
    private[this] def __computeSerializedValue(): _root_.scala.Int = {
      var __size = 0
      
      {
        val __value = worker
        if (__value != edu.uci.ics.amber.engine.common.virtualidentity.ActorVirtualIdentity.defaultInstance) {
          __size += 1 + _root_.com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(__value.serializedSize) + __value.serializedSize
        }
      };
      if (load.nonEmpty) {
        val __localsize = loadSerializedSize
        __size += 1 + _root_.com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(__localsize) + __localsize
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
      {
        val __v = worker
        if (__v != edu.uci.ics.amber.engine.common.virtualidentity.ActorVirtualIdentity.defaultInstance) {
          _output__.writeTag(1, 2)
          _output__.writeUInt32NoTag(__v.serializedSize)
          __v.writeTo(_output__)
        }
      };
      if (load.nonEmpty) {
        _output__.writeTag(2, 2)
        _output__.writeUInt32NoTag(loadSerializedSize)
        load.foreach(_output__.writeInt64NoTag)
      };
    }
    def withWorker(__v: edu.uci.ics.amber.engine.common.virtualidentity.ActorVirtualIdentity): Loads = copy(worker = __v)
    def clearLoad = copy(load = _root_.scala.Seq.empty)
    def addLoad(__vs: _root_.scala.Long*): Loads = addAllLoad(__vs)
    def addAllLoad(__vs: Iterable[_root_.scala.Long]): Loads = copy(load = load ++ __vs)
    def withLoad(__v: _root_.scala.Seq[_root_.scala.Long]): Loads = copy(load = __v)
    def getFieldByNumber(__fieldNumber: _root_.scala.Int): _root_.scala.Any = {
      (__fieldNumber: @_root_.scala.unchecked) match {
        case 1 => {
          val __t = worker
          if (__t != edu.uci.ics.amber.engine.common.virtualidentity.ActorVirtualIdentity.defaultInstance) __t else null
        }
        case 2 => load
      }
    }
    def getField(__field: _root_.scalapb.descriptors.FieldDescriptor): _root_.scalapb.descriptors.PValue = {
      _root_.scala.Predef.require(__field.containingMessage eq companion.scalaDescriptor)
      (__field.number: @_root_.scala.unchecked) match {
        case 1 => worker.toPMessage
        case 2 => _root_.scalapb.descriptors.PRepeated(load.iterator.map(_root_.scalapb.descriptors.PLong(_)).toVector)
      }
    }
    def toProtoString: _root_.scala.Predef.String = _root_.scalapb.TextFormat.printToSingleLineUnicodeString(this)
    def companion = edu.uci.ics.amber.engine.architecture.worker.workloadmetrics.Loads
    // @@protoc_insertion_point(GeneratedMessage[edu.uci.ics.amber.engine.architecture.worker.Loads])
}

object Loads extends scalapb.GeneratedMessageCompanion[edu.uci.ics.amber.engine.architecture.worker.workloadmetrics.Loads] {
  implicit def messageCompanion: scalapb.GeneratedMessageCompanion[edu.uci.ics.amber.engine.architecture.worker.workloadmetrics.Loads] = this
  def parseFrom(`_input__`: _root_.com.google.protobuf.CodedInputStream): edu.uci.ics.amber.engine.architecture.worker.workloadmetrics.Loads = {
    var __worker: _root_.scala.Option[edu.uci.ics.amber.engine.common.virtualidentity.ActorVirtualIdentity] = _root_.scala.None
    val __load: _root_.scala.collection.immutable.VectorBuilder[_root_.scala.Long] = new _root_.scala.collection.immutable.VectorBuilder[_root_.scala.Long]
    var _done__ = false
    while (!_done__) {
      val _tag__ = _input__.readTag()
      _tag__ match {
        case 0 => _done__ = true
        case 10 =>
          __worker = _root_.scala.Some(__worker.fold(_root_.scalapb.LiteParser.readMessage[edu.uci.ics.amber.engine.common.virtualidentity.ActorVirtualIdentity](_input__))(_root_.scalapb.LiteParser.readMessage(_input__, _)))
        case 16 =>
          __load += _input__.readInt64()
        case 18 => {
          val length = _input__.readRawVarint32()
          val oldLimit = _input__.pushLimit(length)
          while (_input__.getBytesUntilLimit > 0) {
            __load += _input__.readInt64()
          }
          _input__.popLimit(oldLimit)
        }
        case tag => _input__.skipField(tag)
      }
    }
    edu.uci.ics.amber.engine.architecture.worker.workloadmetrics.Loads(
        worker = __worker.getOrElse(edu.uci.ics.amber.engine.common.virtualidentity.ActorVirtualIdentity.defaultInstance),
        load = __load.result()
    )
  }
  implicit def messageReads: _root_.scalapb.descriptors.Reads[edu.uci.ics.amber.engine.architecture.worker.workloadmetrics.Loads] = _root_.scalapb.descriptors.Reads{
    case _root_.scalapb.descriptors.PMessage(__fieldsMap) =>
      _root_.scala.Predef.require(__fieldsMap.keys.forall(_.containingMessage eq scalaDescriptor), "FieldDescriptor does not match message type.")
      edu.uci.ics.amber.engine.architecture.worker.workloadmetrics.Loads(
        worker = __fieldsMap.get(scalaDescriptor.findFieldByNumber(1).get).map(_.as[edu.uci.ics.amber.engine.common.virtualidentity.ActorVirtualIdentity]).getOrElse(edu.uci.ics.amber.engine.common.virtualidentity.ActorVirtualIdentity.defaultInstance),
        load = __fieldsMap.get(scalaDescriptor.findFieldByNumber(2).get).map(_.as[_root_.scala.Seq[_root_.scala.Long]]).getOrElse(_root_.scala.Seq.empty)
      )
    case _ => throw new RuntimeException("Expected PMessage")
  }
  def javaDescriptor: _root_.com.google.protobuf.Descriptors.Descriptor = WorkloadmetricsProto.javaDescriptor.getMessageTypes().get(0)
  def scalaDescriptor: _root_.scalapb.descriptors.Descriptor = WorkloadmetricsProto.scalaDescriptor.messages(0)
  def messageCompanionForFieldNumber(__number: _root_.scala.Int): _root_.scalapb.GeneratedMessageCompanion[_] = {
    var __out: _root_.scalapb.GeneratedMessageCompanion[_] = null
    (__number: @_root_.scala.unchecked) match {
      case 1 => __out = edu.uci.ics.amber.engine.common.virtualidentity.ActorVirtualIdentity
    }
    __out
  }
  lazy val nestedMessagesCompanions: Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]] = Seq.empty
  def enumCompanionForFieldNumber(__fieldNumber: _root_.scala.Int): _root_.scalapb.GeneratedEnumCompanion[_] = throw new MatchError(__fieldNumber)
  lazy val defaultInstance = edu.uci.ics.amber.engine.architecture.worker.workloadmetrics.Loads(
    worker = edu.uci.ics.amber.engine.common.virtualidentity.ActorVirtualIdentity.defaultInstance,
    load = _root_.scala.Seq.empty
  )
  implicit class LoadsLens[UpperPB](_l: _root_.scalapb.lenses.Lens[UpperPB, edu.uci.ics.amber.engine.architecture.worker.workloadmetrics.Loads]) extends _root_.scalapb.lenses.ObjectLens[UpperPB, edu.uci.ics.amber.engine.architecture.worker.workloadmetrics.Loads](_l) {
    def worker: _root_.scalapb.lenses.Lens[UpperPB, edu.uci.ics.amber.engine.common.virtualidentity.ActorVirtualIdentity] = field(_.worker)((c_, f_) => c_.copy(worker = f_))
    def load: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Seq[_root_.scala.Long]] = field(_.load)((c_, f_) => c_.copy(load = f_))
  }
  final val WORKER_FIELD_NUMBER = 1
  final val LOAD_FIELD_NUMBER = 2
  def of(
    worker: edu.uci.ics.amber.engine.common.virtualidentity.ActorVirtualIdentity,
    load: _root_.scala.Seq[_root_.scala.Long]
  ): _root_.edu.uci.ics.amber.engine.architecture.worker.workloadmetrics.Loads = _root_.edu.uci.ics.amber.engine.architecture.worker.workloadmetrics.Loads(
    worker,
    load
  )
  // @@protoc_insertion_point(GeneratedMessageCompanion[edu.uci.ics.amber.engine.architecture.worker.Loads])
}
