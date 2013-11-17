/**
 *  Copyright (C) 2013-2014 Duncan DeVore. <http://reactant.org>
 */
package reactant

import sbt._
import Keys._
import com.typesafe.sbt.SbtScalariform
import com.typesafe.sbt.SbtScalariform.ScalariformKeys
import sbtassembly.Plugin.AssemblyKeys._
import sbtassembly.Plugin._
// import com.typesafe.sbt.osgi.SbtOsgi.{ OsgiKeys, osgiSettings, defaultOsgiSettings }
import com.typesafe.sbt.osgi.SbtOsgi
import SbtOsgi._

object BuildSettings {

/** BASIC SETTINGS ****************************************************************************************************/
  val Version = "1.0-SNAPSHOT"

  lazy val basicSettings = seq(
    version                 := Version,
    homepage                := Some(new URL("http://reactant.org")),
    organization            := "org.reactant",
    organizationHomepage    := Some(new URL("http://reactant.org")),
    description             := "Akka-Persistence journal plugin based on Casbah",
    startYear               := Some(2013),
    licenses                := Seq("Apache 2" -> new URL("http://www.apache.org/licenses/LICENSE-2.0.txt")),
    scalaVersion            := "2.10.3",
    resolvers               ++= Dependencies.repos,
    scalacOptions           := Seq(
      "-encoding",
      "utf8",
      "-feature",
      "-unchecked",
      "-deprecation",
      "-target:jvm-1.6",
      "-language:_",
      "-Xlog-reflective-calls"
    )
  )

/** PUBLISH SETTINGS **************************************************************************************************/
  val nexus = "" //"http://repo.eligotech.com/nexus/content/repositories/" DO NOT UNCOMMENT THIS

  val publishSettings = Seq(
    credentials += Credentials(Path.userHome / ".sbt" / ".credentials"),
    publishMavenStyle := true,
    publishTo <<= (version) { (v: String) =>
      if (v.trim.endsWith("SNAPSHOT")) Some("snapshots" at nexus + "vpower-snapshots")
      else                             Some("releases"  at nexus + "vpower-releases")
    }
  )

//  val parentSettings = Seq(
//    publishArtifact in Compile := false
//  )

/** SCALARIFORM SETTINGS **********************************************************************************************/
  lazy val formatSettings = SbtScalariform.scalariformSettings ++ Seq(
    ScalariformKeys.preferences in Compile := formatPreferences,
    ScalariformKeys.preferences in Test    := formatPreferences
  )

  import scalariform.formatter.preferences._
  def formatPreferences = FormattingPreferences()
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

/** TEST SETTINGS *****************************************************************************************************/
  lazy val testSettings = Seq(
    parallelExecution in Test := false,
    parallelExecution in IntegrationTest := false
  )

/** OSGI SETTINGS *****************************************************************************************************/
  val commonExport = Seq("reactant*")

  lazy val osgiSettings = defaultOsgiSettings ++ Seq(
    OsgiKeys.exportPackage := commonExport,
    OsgiKeys.privatePackage := Nil
  )

  // def osgiSettings(exports: Seq[String], imports: Seq[String] = Seq.empty) = SbtOsgi.osgiSettings ++ Seq(
  //   OsgiKeys.exportPackage := exports map { pkg => pkg + ".*;version=\"${Bundle-Version}\"" },
  //   OsgiKeys.importPackage <<= scalaVersion { sv => Seq("""scala.*;version="$<range;[==,=+);%s>"""".format(sv)) },
  //   OsgiKeys.importPackage ++= imports,
  //   OsgiKeys.importPackage += """akka.*;version="$<range;[==,=+);$<@>>"""",
  //   OsgiKeys.importPackage += "*",
  //   OsgiKeys.additionalHeaders := Map("-removeheaders" -> "Include-Resource,Private-Package")
  // )
}
