name := "cst"

version := "0.1"

scalaVersion := "2.13.8"

mainClass in(Compile, run) := Some("pashayan.charlie.cst.commandline.Cst")

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % "test"