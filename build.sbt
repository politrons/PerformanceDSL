name := "PerformanceDSL"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies += "org.scalaz" % "scalaz-core_2.11" % "7.2.14"
libraryDependencies += "org.scalatest" % "scalatest_2.11" % "3.0.1"
libraryDependencies += "org.scala-lang" % "scala-reflect" % "2.11.8"
libraryDependencies += "org.scala-lang" % "scala-library" % "2.11.8"
libraryDependencies += "io.gatling.highcharts" % "gatling-charts-highcharts" % "2.2.2"
libraryDependencies += "io.gatling" % "gatling-test-framework" % "2.2.2"


enablePlugins(GatlingPlugin)
