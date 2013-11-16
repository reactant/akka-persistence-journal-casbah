/**
 *  Copyright (C) 2013-2014 Duncan DeVore. <http://reactant.org>
 */
package reactant

import sbt._

object Dependencies {

  val typeSafeRepo             = "Typesafe Repo"              at "http://repo.typesafe.com/typesafe/releases/"
  val ossSonatypeReleases      = "OSS Sonatype Releases"      at "https://oss.sonatype.org/content/repositories/releases"
  val ossSonatypeSnapshots     = "OSS Sonatype Snapshots"     at "https://oss.sonatype.org/content/repositories/snapshots"

  val resolvers = Seq(typeSafeRepo, ossSonatypeReleases, ossSonatypeSnapshots)

  val akkaPersistence   = "com.typesafe.akka"          %%  "akka-persistence"            % "2.3-SNAPSHOT"
  val casbah            = "org.mongodb"                %%  "casbah"                      % "2.7-SNAPSHOT"
  val scalatest         = "org.scalatest"              %%  "scalatest"                   % "2.10"
}
