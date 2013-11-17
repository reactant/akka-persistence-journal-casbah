/**
 *  Copyright (C) 2013-2014 Duncan DeVore. <http://reactant.org>
 */
package org.reactant.memo.mongo.journal.casbah

import akka.persistence._
import akka.persistence.journal.SyncWriteJournal
import akka.serialization.SerializationExtension

import scala.collection.immutable

private[casbah] class CasbahJournal extends SyncWriteJournal with CasbahReplay {
  val config = context.system.settings.config.getConfig("casbah-journal")

  /**
   * Plugin API: synchronously writes a `persistent` message to the journal.
   */
  def write(persistent: PersistentRepr): Unit =
    println("write")

  /**
   * Plugin API: synchronously writes a batch of persistent messages to the journal.
   * The batch write must be atomic i.e. either all persistent messages in the batch
   * are written or none.
   */
  def writeBatch(persistentBatch: immutable.Seq[PersistentRepr]): Unit =
    println("writeBatch")

  /**
   * Plugin API: synchronously deletes a persistent message. If `physical` is set to
   * `false`, the persistent message is marked as deleted, otherwise it is physically
   * deleted.
   */
  def delete(processorId: String, sequenceNr: Long, physical: Boolean): Unit =
    println("delete")

  /**
   * Plugin API: synchronously writes a delivery confirmation to the journal.
   */
  def confirm(processorId: String, sequenceNr: Long, channelId: String): Unit =
    println("confirm")
}
