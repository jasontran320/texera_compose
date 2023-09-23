package edu.uci.ics.amber.engine.architecture.linksemantics

import edu.uci.ics.amber.engine.architecture.deploysemantics.layer.OpExecConfig
import edu.uci.ics.amber.engine.architecture.sendsemantics.partitionings.{
  Partitioning,
  RangeBasedShufflePartitioning
}
import edu.uci.ics.amber.engine.common.virtualidentity.{ActorVirtualIdentity, LinkIdentity}

class RangeBasedShuffle(
    _from: OpExecConfig,
    _to: OpExecConfig,
    batchSize: Int,
    rangeColumnIndices: Seq[Int],
    rangeMin: Long,
    rangeMax: Long
) extends LinkStrategy(_from, _to, batchSize) {
  override def getPartitioning: Iterable[
    (ActorVirtualIdentity, LinkIdentity, Partitioning, Seq[ActorVirtualIdentity])
  ] = {
    assert(from.isBuilt && to.isBuilt)
    from.identifiers.map(x =>
      (
        x,
        id,
        RangeBasedShufflePartitioning(
          batchSize,
          to.identifiers,
          rangeColumnIndices,
          rangeMin,
          rangeMax
        ),
        to.identifiers.toSeq
      )
    )
  }
}
