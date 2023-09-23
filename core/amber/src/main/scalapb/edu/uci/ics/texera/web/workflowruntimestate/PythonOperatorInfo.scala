// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package edu.uci.ics.texera.web.workflowruntimestate

@SerialVersionUID(0L)
final case class PythonOperatorInfo(
    consoleMessages: _root_.scala.Seq[edu.uci.ics.texera.web.workflowruntimestate.ConsoleMessage] = _root_.scala.Seq.empty,
    evaluateExprResults: _root_.scala.collection.immutable.Map[_root_.scala.Predef.String, edu.uci.ics.texera.web.workflowruntimestate.EvaluatedValueList] = _root_.scala.collection.immutable.Map.empty
    ) extends scalapb.GeneratedMessage with scalapb.lenses.Updatable[PythonOperatorInfo] {
    @transient
    private[this] var __serializedSizeCachedValue: _root_.scala.Int = 0
    private[this] def __computeSerializedValue(): _root_.scala.Int = {
      var __size = 0
      consoleMessages.foreach { __item =>
        val __value = __item
        __size += 1 + _root_.com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(__value.serializedSize) + __value.serializedSize
      }
      evaluateExprResults.foreach { __item =>
        val __value = edu.uci.ics.texera.web.workflowruntimestate.PythonOperatorInfo._typemapper_evaluateExprResults.toBase(__item)
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
      consoleMessages.foreach { __v =>
        val __m = __v
        _output__.writeTag(1, 2)
        _output__.writeUInt32NoTag(__m.serializedSize)
        __m.writeTo(_output__)
      };
      evaluateExprResults.foreach { __v =>
        val __m = edu.uci.ics.texera.web.workflowruntimestate.PythonOperatorInfo._typemapper_evaluateExprResults.toBase(__v)
        _output__.writeTag(2, 2)
        _output__.writeUInt32NoTag(__m.serializedSize)
        __m.writeTo(_output__)
      };
    }
    def clearConsoleMessages = copy(consoleMessages = _root_.scala.Seq.empty)
    def addConsoleMessages(__vs: edu.uci.ics.texera.web.workflowruntimestate.ConsoleMessage*): PythonOperatorInfo = addAllConsoleMessages(__vs)
    def addAllConsoleMessages(__vs: Iterable[edu.uci.ics.texera.web.workflowruntimestate.ConsoleMessage]): PythonOperatorInfo = copy(consoleMessages = consoleMessages ++ __vs)
    def withConsoleMessages(__v: _root_.scala.Seq[edu.uci.ics.texera.web.workflowruntimestate.ConsoleMessage]): PythonOperatorInfo = copy(consoleMessages = __v)
    def clearEvaluateExprResults = copy(evaluateExprResults = _root_.scala.collection.immutable.Map.empty)
    def addEvaluateExprResults(__vs: (_root_.scala.Predef.String, edu.uci.ics.texera.web.workflowruntimestate.EvaluatedValueList)*): PythonOperatorInfo = addAllEvaluateExprResults(__vs)
    def addAllEvaluateExprResults(__vs: Iterable[(_root_.scala.Predef.String, edu.uci.ics.texera.web.workflowruntimestate.EvaluatedValueList)]): PythonOperatorInfo = copy(evaluateExprResults = evaluateExprResults ++ __vs)
    def withEvaluateExprResults(__v: _root_.scala.collection.immutable.Map[_root_.scala.Predef.String, edu.uci.ics.texera.web.workflowruntimestate.EvaluatedValueList]): PythonOperatorInfo = copy(evaluateExprResults = __v)
    def getFieldByNumber(__fieldNumber: _root_.scala.Int): _root_.scala.Any = {
      (__fieldNumber: @_root_.scala.unchecked) match {
        case 1 => consoleMessages
        case 2 => evaluateExprResults.iterator.map(edu.uci.ics.texera.web.workflowruntimestate.PythonOperatorInfo._typemapper_evaluateExprResults.toBase(_)).toSeq
      }
    }
    def getField(__field: _root_.scalapb.descriptors.FieldDescriptor): _root_.scalapb.descriptors.PValue = {
      _root_.scala.Predef.require(__field.containingMessage eq companion.scalaDescriptor)
      (__field.number: @_root_.scala.unchecked) match {
        case 1 => _root_.scalapb.descriptors.PRepeated(consoleMessages.iterator.map(_.toPMessage).toVector)
        case 2 => _root_.scalapb.descriptors.PRepeated(evaluateExprResults.iterator.map(edu.uci.ics.texera.web.workflowruntimestate.PythonOperatorInfo._typemapper_evaluateExprResults.toBase(_).toPMessage).toVector)
      }
    }
    def toProtoString: _root_.scala.Predef.String = _root_.scalapb.TextFormat.printToSingleLineUnicodeString(this)
    def companion = edu.uci.ics.texera.web.workflowruntimestate.PythonOperatorInfo
    // @@protoc_insertion_point(GeneratedMessage[edu.uci.ics.texera.web.PythonOperatorInfo])
}

object PythonOperatorInfo extends scalapb.GeneratedMessageCompanion[edu.uci.ics.texera.web.workflowruntimestate.PythonOperatorInfo] {
  implicit def messageCompanion: scalapb.GeneratedMessageCompanion[edu.uci.ics.texera.web.workflowruntimestate.PythonOperatorInfo] = this
  def parseFrom(`_input__`: _root_.com.google.protobuf.CodedInputStream): edu.uci.ics.texera.web.workflowruntimestate.PythonOperatorInfo = {
    val __consoleMessages: _root_.scala.collection.immutable.VectorBuilder[edu.uci.ics.texera.web.workflowruntimestate.ConsoleMessage] = new _root_.scala.collection.immutable.VectorBuilder[edu.uci.ics.texera.web.workflowruntimestate.ConsoleMessage]
    val __evaluateExprResults: _root_.scala.collection.mutable.Builder[(_root_.scala.Predef.String, edu.uci.ics.texera.web.workflowruntimestate.EvaluatedValueList), _root_.scala.collection.immutable.Map[_root_.scala.Predef.String, edu.uci.ics.texera.web.workflowruntimestate.EvaluatedValueList]] = _root_.scala.collection.immutable.Map.newBuilder[_root_.scala.Predef.String, edu.uci.ics.texera.web.workflowruntimestate.EvaluatedValueList]
    var _done__ = false
    while (!_done__) {
      val _tag__ = _input__.readTag()
      _tag__ match {
        case 0 => _done__ = true
        case 10 =>
          __consoleMessages += _root_.scalapb.LiteParser.readMessage[edu.uci.ics.texera.web.workflowruntimestate.ConsoleMessage](_input__)
        case 18 =>
          __evaluateExprResults += edu.uci.ics.texera.web.workflowruntimestate.PythonOperatorInfo._typemapper_evaluateExprResults.toCustom(_root_.scalapb.LiteParser.readMessage[edu.uci.ics.texera.web.workflowruntimestate.PythonOperatorInfo.EvaluateExprResultsEntry](_input__))
        case tag => _input__.skipField(tag)
      }
    }
    edu.uci.ics.texera.web.workflowruntimestate.PythonOperatorInfo(
        consoleMessages = __consoleMessages.result(),
        evaluateExprResults = __evaluateExprResults.result()
    )
  }
  implicit def messageReads: _root_.scalapb.descriptors.Reads[edu.uci.ics.texera.web.workflowruntimestate.PythonOperatorInfo] = _root_.scalapb.descriptors.Reads{
    case _root_.scalapb.descriptors.PMessage(__fieldsMap) =>
      _root_.scala.Predef.require(__fieldsMap.keys.forall(_.containingMessage eq scalaDescriptor), "FieldDescriptor does not match message type.")
      edu.uci.ics.texera.web.workflowruntimestate.PythonOperatorInfo(
        consoleMessages = __fieldsMap.get(scalaDescriptor.findFieldByNumber(1).get).map(_.as[_root_.scala.Seq[edu.uci.ics.texera.web.workflowruntimestate.ConsoleMessage]]).getOrElse(_root_.scala.Seq.empty),
        evaluateExprResults = __fieldsMap.get(scalaDescriptor.findFieldByNumber(2).get).map(_.as[_root_.scala.Seq[edu.uci.ics.texera.web.workflowruntimestate.PythonOperatorInfo.EvaluateExprResultsEntry]]).getOrElse(_root_.scala.Seq.empty).iterator.map(edu.uci.ics.texera.web.workflowruntimestate.PythonOperatorInfo._typemapper_evaluateExprResults.toCustom(_)).toMap
      )
    case _ => throw new RuntimeException("Expected PMessage")
  }
  def javaDescriptor: _root_.com.google.protobuf.Descriptors.Descriptor = WorkflowruntimestateProto.javaDescriptor.getMessageTypes().get(5)
  def scalaDescriptor: _root_.scalapb.descriptors.Descriptor = WorkflowruntimestateProto.scalaDescriptor.messages(5)
  def messageCompanionForFieldNumber(__number: _root_.scala.Int): _root_.scalapb.GeneratedMessageCompanion[_] = {
    var __out: _root_.scalapb.GeneratedMessageCompanion[_] = null
    (__number: @_root_.scala.unchecked) match {
      case 1 => __out = edu.uci.ics.texera.web.workflowruntimestate.ConsoleMessage
      case 2 => __out = edu.uci.ics.texera.web.workflowruntimestate.PythonOperatorInfo.EvaluateExprResultsEntry
    }
    __out
  }
  lazy val nestedMessagesCompanions: Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]] =
    Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]](
      _root_.edu.uci.ics.texera.web.workflowruntimestate.PythonOperatorInfo.EvaluateExprResultsEntry
    )
  def enumCompanionForFieldNumber(__fieldNumber: _root_.scala.Int): _root_.scalapb.GeneratedEnumCompanion[_] = throw new MatchError(__fieldNumber)
  lazy val defaultInstance = edu.uci.ics.texera.web.workflowruntimestate.PythonOperatorInfo(
    consoleMessages = _root_.scala.Seq.empty,
    evaluateExprResults = _root_.scala.collection.immutable.Map.empty
  )
  @SerialVersionUID(0L)
  final case class EvaluateExprResultsEntry(
      key: _root_.scala.Predef.String = "",
      value: _root_.scala.Option[edu.uci.ics.texera.web.workflowruntimestate.EvaluatedValueList] = _root_.scala.None
      ) extends scalapb.GeneratedMessage with scalapb.lenses.Updatable[EvaluateExprResultsEntry] {
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
        if (value.isDefined) {
          val __value = value.get
          __size += 1 + _root_.com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(__value.serializedSize) + __value.serializedSize
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
        value.foreach { __v =>
          val __m = __v
          _output__.writeTag(2, 2)
          _output__.writeUInt32NoTag(__m.serializedSize)
          __m.writeTo(_output__)
        };
      }
      def withKey(__v: _root_.scala.Predef.String): EvaluateExprResultsEntry = copy(key = __v)
      def getValue: edu.uci.ics.texera.web.workflowruntimestate.EvaluatedValueList = value.getOrElse(edu.uci.ics.texera.web.workflowruntimestate.EvaluatedValueList.defaultInstance)
      def clearValue: EvaluateExprResultsEntry = copy(value = _root_.scala.None)
      def withValue(__v: edu.uci.ics.texera.web.workflowruntimestate.EvaluatedValueList): EvaluateExprResultsEntry = copy(value = Option(__v))
      def getFieldByNumber(__fieldNumber: _root_.scala.Int): _root_.scala.Any = {
        (__fieldNumber: @_root_.scala.unchecked) match {
          case 1 => {
            val __t = key
            if (__t != "") __t else null
          }
          case 2 => value.orNull
        }
      }
      def getField(__field: _root_.scalapb.descriptors.FieldDescriptor): _root_.scalapb.descriptors.PValue = {
        _root_.scala.Predef.require(__field.containingMessage eq companion.scalaDescriptor)
        (__field.number: @_root_.scala.unchecked) match {
          case 1 => _root_.scalapb.descriptors.PString(key)
          case 2 => value.map(_.toPMessage).getOrElse(_root_.scalapb.descriptors.PEmpty)
        }
      }
      def toProtoString: _root_.scala.Predef.String = _root_.scalapb.TextFormat.printToSingleLineUnicodeString(this)
      def companion = edu.uci.ics.texera.web.workflowruntimestate.PythonOperatorInfo.EvaluateExprResultsEntry
      // @@protoc_insertion_point(GeneratedMessage[edu.uci.ics.texera.web.PythonOperatorInfo.EvaluateExprResultsEntry])
  }
  
  object EvaluateExprResultsEntry extends scalapb.GeneratedMessageCompanion[edu.uci.ics.texera.web.workflowruntimestate.PythonOperatorInfo.EvaluateExprResultsEntry] {
    implicit def messageCompanion: scalapb.GeneratedMessageCompanion[edu.uci.ics.texera.web.workflowruntimestate.PythonOperatorInfo.EvaluateExprResultsEntry] = this
    def parseFrom(`_input__`: _root_.com.google.protobuf.CodedInputStream): edu.uci.ics.texera.web.workflowruntimestate.PythonOperatorInfo.EvaluateExprResultsEntry = {
      var __key: _root_.scala.Predef.String = ""
      var __value: _root_.scala.Option[edu.uci.ics.texera.web.workflowruntimestate.EvaluatedValueList] = _root_.scala.None
      var _done__ = false
      while (!_done__) {
        val _tag__ = _input__.readTag()
        _tag__ match {
          case 0 => _done__ = true
          case 10 =>
            __key = _input__.readStringRequireUtf8()
          case 18 =>
            __value = Option(__value.fold(_root_.scalapb.LiteParser.readMessage[edu.uci.ics.texera.web.workflowruntimestate.EvaluatedValueList](_input__))(_root_.scalapb.LiteParser.readMessage(_input__, _)))
          case tag => _input__.skipField(tag)
        }
      }
      edu.uci.ics.texera.web.workflowruntimestate.PythonOperatorInfo.EvaluateExprResultsEntry(
          key = __key,
          value = __value
      )
    }
    implicit def messageReads: _root_.scalapb.descriptors.Reads[edu.uci.ics.texera.web.workflowruntimestate.PythonOperatorInfo.EvaluateExprResultsEntry] = _root_.scalapb.descriptors.Reads{
      case _root_.scalapb.descriptors.PMessage(__fieldsMap) =>
        _root_.scala.Predef.require(__fieldsMap.keys.forall(_.containingMessage eq scalaDescriptor), "FieldDescriptor does not match message type.")
        edu.uci.ics.texera.web.workflowruntimestate.PythonOperatorInfo.EvaluateExprResultsEntry(
          key = __fieldsMap.get(scalaDescriptor.findFieldByNumber(1).get).map(_.as[_root_.scala.Predef.String]).getOrElse(""),
          value = __fieldsMap.get(scalaDescriptor.findFieldByNumber(2).get).flatMap(_.as[_root_.scala.Option[edu.uci.ics.texera.web.workflowruntimestate.EvaluatedValueList]])
        )
      case _ => throw new RuntimeException("Expected PMessage")
    }
    def javaDescriptor: _root_.com.google.protobuf.Descriptors.Descriptor = edu.uci.ics.texera.web.workflowruntimestate.PythonOperatorInfo.javaDescriptor.getNestedTypes().get(0)
    def scalaDescriptor: _root_.scalapb.descriptors.Descriptor = edu.uci.ics.texera.web.workflowruntimestate.PythonOperatorInfo.scalaDescriptor.nestedMessages(0)
    def messageCompanionForFieldNumber(__number: _root_.scala.Int): _root_.scalapb.GeneratedMessageCompanion[_] = {
      var __out: _root_.scalapb.GeneratedMessageCompanion[_] = null
      (__number: @_root_.scala.unchecked) match {
        case 2 => __out = edu.uci.ics.texera.web.workflowruntimestate.EvaluatedValueList
      }
      __out
    }
    lazy val nestedMessagesCompanions: Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]] = Seq.empty
    def enumCompanionForFieldNumber(__fieldNumber: _root_.scala.Int): _root_.scalapb.GeneratedEnumCompanion[_] = throw new MatchError(__fieldNumber)
    lazy val defaultInstance = edu.uci.ics.texera.web.workflowruntimestate.PythonOperatorInfo.EvaluateExprResultsEntry(
      key = "",
      value = _root_.scala.None
    )
    implicit class EvaluateExprResultsEntryLens[UpperPB](_l: _root_.scalapb.lenses.Lens[UpperPB, edu.uci.ics.texera.web.workflowruntimestate.PythonOperatorInfo.EvaluateExprResultsEntry]) extends _root_.scalapb.lenses.ObjectLens[UpperPB, edu.uci.ics.texera.web.workflowruntimestate.PythonOperatorInfo.EvaluateExprResultsEntry](_l) {
      def key: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Predef.String] = field(_.key)((c_, f_) => c_.copy(key = f_))
      def value: _root_.scalapb.lenses.Lens[UpperPB, edu.uci.ics.texera.web.workflowruntimestate.EvaluatedValueList] = field(_.getValue)((c_, f_) => c_.copy(value = Option(f_)))
      def optionalValue: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Option[edu.uci.ics.texera.web.workflowruntimestate.EvaluatedValueList]] = field(_.value)((c_, f_) => c_.copy(value = f_))
    }
    final val KEY_FIELD_NUMBER = 1
    final val VALUE_FIELD_NUMBER = 2
    @transient
    implicit val keyValueMapper: _root_.scalapb.TypeMapper[edu.uci.ics.texera.web.workflowruntimestate.PythonOperatorInfo.EvaluateExprResultsEntry, (_root_.scala.Predef.String, edu.uci.ics.texera.web.workflowruntimestate.EvaluatedValueList)] =
      _root_.scalapb.TypeMapper[edu.uci.ics.texera.web.workflowruntimestate.PythonOperatorInfo.EvaluateExprResultsEntry, (_root_.scala.Predef.String, edu.uci.ics.texera.web.workflowruntimestate.EvaluatedValueList)](__m => (__m.key, __m.getValue))(__p => edu.uci.ics.texera.web.workflowruntimestate.PythonOperatorInfo.EvaluateExprResultsEntry(__p._1, Some(__p._2)))
    def of(
      key: _root_.scala.Predef.String,
      value: _root_.scala.Option[edu.uci.ics.texera.web.workflowruntimestate.EvaluatedValueList]
    ): _root_.edu.uci.ics.texera.web.workflowruntimestate.PythonOperatorInfo.EvaluateExprResultsEntry = _root_.edu.uci.ics.texera.web.workflowruntimestate.PythonOperatorInfo.EvaluateExprResultsEntry(
      key,
      value
    )
    // @@protoc_insertion_point(GeneratedMessageCompanion[edu.uci.ics.texera.web.PythonOperatorInfo.EvaluateExprResultsEntry])
  }
  
  implicit class PythonOperatorInfoLens[UpperPB](_l: _root_.scalapb.lenses.Lens[UpperPB, edu.uci.ics.texera.web.workflowruntimestate.PythonOperatorInfo]) extends _root_.scalapb.lenses.ObjectLens[UpperPB, edu.uci.ics.texera.web.workflowruntimestate.PythonOperatorInfo](_l) {
    def consoleMessages: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Seq[edu.uci.ics.texera.web.workflowruntimestate.ConsoleMessage]] = field(_.consoleMessages)((c_, f_) => c_.copy(consoleMessages = f_))
    def evaluateExprResults: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.collection.immutable.Map[_root_.scala.Predef.String, edu.uci.ics.texera.web.workflowruntimestate.EvaluatedValueList]] = field(_.evaluateExprResults)((c_, f_) => c_.copy(evaluateExprResults = f_))
  }
  final val CONSOLE_MESSAGES_FIELD_NUMBER = 1
  final val EVALUATE_EXPR_RESULTS_FIELD_NUMBER = 2
  @transient
  private[workflowruntimestate] val _typemapper_evaluateExprResults: _root_.scalapb.TypeMapper[edu.uci.ics.texera.web.workflowruntimestate.PythonOperatorInfo.EvaluateExprResultsEntry, (_root_.scala.Predef.String, edu.uci.ics.texera.web.workflowruntimestate.EvaluatedValueList)] = implicitly[_root_.scalapb.TypeMapper[edu.uci.ics.texera.web.workflowruntimestate.PythonOperatorInfo.EvaluateExprResultsEntry, (_root_.scala.Predef.String, edu.uci.ics.texera.web.workflowruntimestate.EvaluatedValueList)]]
  def of(
    consoleMessages: _root_.scala.Seq[edu.uci.ics.texera.web.workflowruntimestate.ConsoleMessage],
    evaluateExprResults: _root_.scala.collection.immutable.Map[_root_.scala.Predef.String, edu.uci.ics.texera.web.workflowruntimestate.EvaluatedValueList]
  ): _root_.edu.uci.ics.texera.web.workflowruntimestate.PythonOperatorInfo = _root_.edu.uci.ics.texera.web.workflowruntimestate.PythonOperatorInfo(
    consoleMessages,
    evaluateExprResults
  )
  // @@protoc_insertion_point(GeneratedMessageCompanion[edu.uci.ics.texera.web.PythonOperatorInfo])
}
