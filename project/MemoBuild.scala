/**
 *  Copyright (C) 2013-2014 Duncan DeVore. <http://reactant.org>
 */
package memo

import sbt._
import Keys._

object MemoBuild extends Build {
  import Dependencies._
  import BuildSettings._

  lazy val memo = Project(id = "memo", base = file("."))
    .aggregate(memoMongo, memoExample)
    .settings(basicSettings: _*)

  lazy val memoMongo = Project(id = "memo-mongo", base = file("memo-mongo"))
    .settings(basicSettings: _*)
    .settings(formatSettings: _*)
    .settings(osgiSettings(exports = Seq("org.reactant.memo.mongo")): _*)
    .settings(testSettings: _*)
    .settings(libraryDependencies ++=
      provided(akkaPersistence, casbah) ++
      test(scalatest))

  // TODO rename dir to memo-example
  lazy val memoExample = Project(id = "example", base = file("example"))
    .dependsOn(memoMongo)
    .settings(basicSettings: _*)
    .settings(formatSettings: _*)
    .settings(testSettings: _*)
}
