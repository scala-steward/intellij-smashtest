//intellijPluginName in ThisBuild := "IntelliJ-Smashtest"
//intellijBuild in ThisBuild := "192.6817.14"
//intellijDownloadSources in ThisBuild := true
//intellijInternalPlugins += "java"
//intellijExternalPlugins += "PsiViewer".toPlugin

lazy val intellijSmashtest = (project in file("."))
//  .enablePlugins(SbtIdeaPlugin)
  .settings(
    name := "IntelliJ Smashtest",
    version := "0.1.0",
    scalaVersion := "2.13.1",
    javacOptions in Global ++= Seq("-source", "1.8", "-target", "1.8"),
    scalacOptions in Global ++= Seq("-target:jvm-1.8", "-deprecation", "-feature"),
//    unmanagedSourceDirectories in Compile += baseDirectory.value / "gen",
    libraryDependencies ++= Seq(
      "org.eclipse" % "org.eclipse.tm4e.core" % "0.3.4-SNAPSHOT",
      "org.scalatest" %% "scalatest" % "3.0.8" % Test
    )
  )
