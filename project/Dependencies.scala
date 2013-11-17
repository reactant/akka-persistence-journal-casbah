/**
 *  Copyright (C) 2013-2014 Duncan DeVore. <http://reactant.org>
 */
package memo

import sbt._

object Dependencies {

  val typeSafeReleases         = "Typesafe Releases"          at "http://repo.typesafe.com/typesafe/releases/"
  val typeSafeSnapshots        = "Typesafe Snapshots"         at "http://repo.typesafe.com/typesafe/snapshots/"
  val ossSonatypeReleases      = "OSS Sonatype Releases"      at "https://oss.sonatype.org/content/repositories/releases"
  val ossSonatypeSnapshots     = "OSS Sonatype Snapshots"     at "https://oss.sonatype.org/content/repositories/snapshots"

  val repos = Seq(typeSafeReleases, typeSafeSnapshots, ossSonatypeReleases, ossSonatypeSnapshots)

  def compile   (deps: ModuleID*): Seq[ModuleID] = deps map (_ % "compile")
  def provided  (deps: ModuleID*): Seq[ModuleID] = deps map (_ % "provided")
  def test      (deps: ModuleID*): Seq[ModuleID] = deps map (_ % "test")

  val akkaPersistence   = "com.typesafe.akka"          %%  "akka-persistence-experimental"        % "2.3-SNAPSHOT"
  val casbah            = "org.mongodb"                %%  "casbah"                               % "2.6.3"
  val scalatest         = "org.scalatest"               %  "scalatest_2.10"                       % "2.0"
}
