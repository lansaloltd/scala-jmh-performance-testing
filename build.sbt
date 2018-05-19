import sbt.Keys._

lazy val root = (project in file(".")).
  enablePlugins(JmhPlugin).
  settings(
    name := "scala-jmh-performance-testing",
    organization := "LansaloLtd",
    version := "1.0",
    scalaVersion := "2.12.3"
  )
