/**
 *  Copyright (C) 2013-2014 Duncan DeVore. <http://reactant.org>
 */
package org.reactant.memo.mongo.journal.casbah

import akka.persistence._
import akka.persistence.journal.SyncWriteJournal
import akka.serialization.SerializationExtension

import scala.collection.immutable

// private[casbah] class CasbahJournal extends SyncWriteJournal with CasbahReplay {
class CasbahJournal extends SyncWriteJournal with CasbahReplay {
  val config = context.system.settings.config.getConfig("casbah-journal")

  val serialization = SerializationExtension(context.system)

  def persistToBytes(p: PersistentRepr): Array[Byte] = serialization.serialize(p).get
  def persistFromBytes(a: Array[Byte]) = serialization.deserialize(a, classOf[PersistentRepr]).get

  /**
   * Plugin API: synchronously writes a batch of persistent messages to the journal.
   * The batch write must be atomic i.e. either all persistent messages in the batch
   * are written or none.
   */
  def write(persistentBatch: immutable.Seq[PersistentRepr]): Unit = {
    persistentBatch.foreach { p ⇒
      println(p.sequenceNr)
    }
  }

  /**
   * Plugin API: synchronously deletes a persistent message. If `physical` is set to
   * `false`, the persistent message is marked as deleted, otherwise it is physically
   * deleted.
   */
  def delete(processorId: String, fromSequenceNr: Long, toSequenceNr: Long, permanent: Boolean): Unit =
    println("delete")

  /**
   * Plugin API: synchronously writes a delivery confirmation to the journal.
   */
  def confirm(processorId: String, sequenceNr: Long, channelId: String): Unit =
    println("confirm")
}
