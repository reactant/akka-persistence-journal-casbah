/**
 *  Copyright (C) 2013-2014 Duncan DeVore. <http://reactant.org>
 */
package memo

import sbt._
import Keys._

import com.typesafe.sbt.SbtSite.site
import com.typesafe.sbt.site.SphinxSupport
import com.typesafe.sbt.site.SphinxSupport._

object MemoBuild extends Build {
  import Dependencies._
  import BuildSettings._

  lazy val memo = Project(id = "memo", base = file("."))
    .aggregate(memoDocs, memoMongo, memoExample)
    .settings(basicSettings: _*)

  // TODO: move site settings SiteSettings.scala
  lazy val memoDocs = Project(id = "memo-docs", base = file("memo-docs"))
    .settings(basicSettings: _*)
    .settings(site.settings: _*)
    .settings(site.sphinxSupport(): _*)
    .settings(Seq(sourceDirectory in Sphinx <<= baseDirectory): _*)

  lazy val memoMongo = Project(id = "memo-mongo", base = file("memo-mongo"))
    .settings(moduleSettings: _*)
    .settings(testSettings: _*)
    .settings(osgiSettings(exports = Seq("org.reactant.memo.mongo")): _*)
    .settings(libraryDependencies ++=
      provided(akkaPersistence, casbah) ++
      test(scalatest))

  // TODO rename dir to memo-example
  lazy val memoExample = Project(id = "memo-example", base = file("memo-example"))
    .dependsOn(memoMongo)
    .settings(moduleSettings: _*)
    .settings(testSettings: _*)
}
