package edu.uci.ics.amber.engine.architecture.linksemantics

import edu.uci.ics.amber.engine.architecture.deploysemantics.layer.OpExecConfig
import edu.uci.ics.amber.engine.architecture.sendsemantics.partitionings.{
  HashBasedShufflePartitioning,
  Partitioning
}
import edu.uci.ics.amber.engine.common.virtualidentity.{ActorVirtualIdentity, LinkIdentity}

class HashBasedShuffle(
    _from: OpExecConfig,
    _to: OpExecConfig,
    batchSize: Int,
    hashColumnIndices: Seq[Int]
) extends LinkStrategy(_from, _to, batchSize) {
  override def getPartitioning: Iterable[
    (ActorVirtualIdentity, LinkIdentity, Partitioning, Seq[ActorVirtualIdentity])
  ] = {
    assert(from.isBuilt && to.isBuilt)
    from.identifiers.map(x =>
      (
        x,
        id,
        HashBasedShufflePartitioning(batchSize, to.identifiers, hashColumnIndices),
        to.identifiers.toSeq
      )
    )
  }

}
