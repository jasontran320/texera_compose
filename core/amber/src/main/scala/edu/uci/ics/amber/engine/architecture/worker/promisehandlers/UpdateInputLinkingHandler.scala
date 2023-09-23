package edu.uci.ics.amber.engine.architecture.worker.promisehandlers

import edu.uci.ics.amber.engine.architecture.worker.WorkerAsyncRPCHandlerInitializer
import edu.uci.ics.amber.engine.architecture.worker.promisehandlers.UpdateInputLinkingHandler.UpdateInputLinking
import edu.uci.ics.amber.engine.architecture.worker.statistics.WorkerState.{PAUSED, READY, RUNNING}
import edu.uci.ics.amber.engine.common.rpc.AsyncRPCServer.ControlCommand
import edu.uci.ics.amber.engine.common.virtualidentity.{ActorVirtualIdentity, LinkIdentity}

object UpdateInputLinkingHandler {

  final case class UpdateInputLinking(identifier: ActorVirtualIdentity, inputLink: LinkIdentity)
      extends ControlCommand[Unit]
}

trait UpdateInputLinkingHandler {
  this: WorkerAsyncRPCHandlerInitializer =>

  registerHandler { (msg: UpdateInputLinking, sender) =>
    stateManager.assertState(READY, RUNNING, PAUSED)
    dataProcessor.registerInput(msg.identifier, msg.inputLink)
    upstreamLinkStatus.registerInput(msg.identifier, msg.inputLink)
  }

}
