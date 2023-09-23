package edu.uci.ics.amber.engine.architecture.sendsemantics.partitioners

import edu.uci.ics.amber.engine.architecture.messaginglayer.NetworkOutputPort
import edu.uci.ics.amber.engine.common.Constants
import edu.uci.ics.amber.engine.common.ambermessage.{
  DataFrame,
  DataPayload,
  EndOfUpstream,
  EpochMarker
}
import edu.uci.ics.amber.engine.common.tuple.ITuple
import edu.uci.ics.amber.engine.common.virtualidentity.ActorVirtualIdentity

import scala.collection.mutable.ArrayBuffer

trait Partitioner extends Serializable {
  def getBucketIndex(tuple: ITuple): Iterator[Int]

  def allReceivers: Seq[ActorVirtualIdentity]
}

class NetworkOutputBuffer(
    val to: ActorVirtualIdentity,
    val dataOutputPort: NetworkOutputPort[DataPayload],
    val batchSize: Int = Constants.defaultBatchSize
) {

  var buffer = new ArrayBuffer[ITuple]()

  def addTuple(tuple: ITuple): Unit = {
    buffer.append(tuple)
    if (buffer.size >= batchSize) {
      flush()
    }
  }

  def addEpochMarker(epochMarker: EpochMarker): Unit = {
    flush()
    dataOutputPort.sendTo(to, epochMarker)
  }

  def noMore(): Unit = {
    flush()
    dataOutputPort.sendTo(to, EndOfUpstream())
  }

  def flush(): Unit = {
    if (buffer.nonEmpty) {
      dataOutputPort.sendTo(to, DataFrame(buffer.toArray))
      buffer = new ArrayBuffer[ITuple]()
    }
  }

}
