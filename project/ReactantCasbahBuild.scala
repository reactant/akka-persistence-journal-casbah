/**
 *  Copyright (C) 2013-2014 Duncan DeVore. <http://reactant.org>
 */
package reactant

import sbt._
import Keys._

object ReactantCasbahBuild extends Build {
  import Dependencies._
  import BuildSettings._

  lazy val defaultSettings =
    basicSettings ++
    formatSettings ++
    osgiSettings ++
    publishSettings ++
    testSettings

  lazy val reactantCasbah = Project(id = "reactant-casbah", base = file("."))
    .aggregate(journal, snapshot, example)
    .settings(basicSettings: _*)

  lazy val journal = Project(id = "reactant-casbah-journal", base = file("reactant-casbah-journal"))
    .settings(basicSettings: _*)
    .settings(formatSettings: _*)
    .settings(osgiSettings: _*)
    .settings(testSettings: _*)
    .settings(libraryDependencies ++=
      provided(akkaPersistence, casbah) ++
      test(scalatest))

  lazy val snapshot = Project(id = "reactant-casbah-snapshot", base = file("reactant-casbah-snapshot"))
    .settings(basicSettings: _*)
    .settings(formatSettings: _*)
    .settings(osgiSettings: _*)
    .settings(testSettings: _*)
    .settings(libraryDependencies ++=
      provided(akkaPersistence, casbah) ++
      test(scalatest))

  lazy val example = Project(id = "example", base = file("example"))
    .dependsOn(journal, snapshot)
    .settings(basicSettings: _*)
    .settings(formatSettings: _*)
    .settings(osgiSettings: _*)
    .settings(testSettings: _*)
}
