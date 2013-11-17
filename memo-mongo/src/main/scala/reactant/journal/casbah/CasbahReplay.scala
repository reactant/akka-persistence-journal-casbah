/**
 *  Copyright (C) 2013-2014 Duncan DeVore. <http://reactant.org>
 */
package reactant.journal.casbah

import akka.persistence._
import akka.persistence.journal.AsyncReplay

import scala.concurrent.Future

private[reactant] trait CasbahReplay extends AsyncReplay { this: CasbahJournal ⇒

  private val replayDispatcherId = context.system.settings.config.getString("casbah-journal.replay-dispatcher")
  private val replayDispatcher = context.system.dispatchers.lookup(replayDispatcherId)

  def replayAsync(processorId: String, fromSequenceNr: Long, toSequenceNr: Long)(replayCallback: PersistentRepr ⇒ Unit): Future[Long] = Future(1L)(replayDispatcher)
}
