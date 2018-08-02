import sbt.Keys._

lazy val root = (project in file(".")).
  enablePlugins(JmhPlugin).
  settings(
    name := "scala-jmh-performance-testing",
    organization := "LansaloLtd",
    version := "1.0",
    scalaVersion := "2.12.3",
    libraryDependencies ++= Seq(
      "org.openjdk.jmh" % "jmh-generator-annprocess" % "1.21",
      "commons-codec" % "commons-codec" % "1.9",
      "com.storm-enroute" %% "scalameter" % "0.8.2",
      "org.scalatest" %% "scalatest" % "3.0.1" % Test
    ),
    testFrameworks += new TestFramework("org.scalameter.ScalaMeterFramework"),
    parallelExecution in Test := false
  )

