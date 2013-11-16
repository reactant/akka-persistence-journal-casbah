/**
 *  Copyright (C) 2013-2014 Duncan DeVore. <http://reactant.org>
 */

package reactant

import sbt._
import Keys._

// TODO Publish Settings
object Publish {
  val nexus = "" //"http://repo.eligotech.com/nexus/content/repositories/" DO NOT UNCOMMENT THIS

  // val defaultSettings = Seq(
  //   credentials += Credentials(Path.userHome / ".sbt" / ".credentials"),
  //   publishMavenStyle := true,
  //   publishTo <<= (version) { (v: String) =>
  //     if (v.trim.endsWith("SNAPSHOT")) Some("snapshots" at nexus + "vpower-snapshots")
  //     else                             Some("releases"  at nexus + "vpower-releases")
  //   }
  // )

  // val parentSettings = Seq(
  //   publishArtifact in Compile := false
  // )
}

object Tests {
  val defaultSettings = Seq(
    parallelExecution in Test := false,
    parallelExecution in IntegrationTest := false
  )
}

object Osgi {
  val commonExport = Seq(
    "org.reactant*"
  )

  val defaultSettings = defaultOsgiSettings ++ Seq(
    OsgiKeys.exportPackage := commonExport,
    OsgiKeys.privatePackage := Nil
  )
}

object Formatting {
  import com.typesafe.sbt.SbtScalariform._

  lazy val defaultSettings = scalariformSettings ++ Seq(
    ScalariformKeys.preferences in Compile := defaultPreferences,
    ScalariformKeys.preferences in Test    := defaultPreferences
  )

  val defaultPreferences = {
    import scalariform.formatter.preferences._
    FormattingPreferences()
      .setPreference(AlignParameters, false)
      .setPreference(AlignSingleLineCaseStatements, true)
      .setPreference(CompactControlReadability, true)
      .setPreference(CompactStringConcatenation, false)
      .setPreference(DoubleIndentClassDeclaration, true)
      .setPreference(FormatXml, true)
      .setPreference(IndentLocalDefs, false)
      .setPreference(IndentPackageBlocks, true)
      .setPreference(IndentSpaces, 2)
      .setPreference(MultilineScaladocCommentsStartOnFirstLine, false)
      .setPreference(PreserveSpaceBeforeArguments, false)
      .setPreference(PreserveDanglingCloseParenthesis, false)
      .setPreference(RewriteArrowSymbols, true)
      .setPreference(SpaceBeforeColon, false)
      .setPreference(SpaceInsideBrackets, false)
      .setPreference(SpacesWithinPatternBinders, true)
  }
}

object ReactantCasbahBuild extends Build {
  import Dependencies._

  lazy val defaultSettings =
    Defaults.defaultSettings ++
      Compiler.defaultSettings ++
      Publish.defaultSettings ++
      Tests.defaultSettings ++
      Osgi.defaultSettings ++
      Formatting.defaultSettings ++
      assemblySettings

  lazy val reactantCasbah = Project(
    id = "reactant-casbah",
    base = file("."),
    settings = defaultSettings ++ packSettings
  ).aggregate(example, journal, snapshot)

  lazy val journal = Project(
    id = "reactant-casbah-journal",
    base = file("reactant-casbah-journal"),
    settings = defaultSettings
  )

  lazy val snapshot = Project(
    id = "reactant-casbah-snapshot",
    base = file("reactant-casbah-snapshot"),
    settings = defaultSettings
  )

  lazy val example = Project(
    id = "example",
    base = file("example"),
    settings = defaultSettings
  ).dependsOn(journal, snapshot)
}
