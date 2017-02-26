lazy val commonSettings = Seq(
  organization := "com.example",
  version := "0.0.1-SNAPSHOT",
  scalaVersion := "2.11.8",
  crossScalaVersions := Seq("2.11.8", "2.10.6"),
  libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"
)

lazy val root = (project in file("."))
  .aggregate(binco)

lazy val binco = (project in file("binco"))
  .settings(commonSettings)
  .settings(
    name := "binco"
  )
