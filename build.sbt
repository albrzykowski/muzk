scalaVersion := "2.12.11"

name := "Muzk"
organization := "io.github.albrzykowski.muzk"
version := "0.1.0-SNAPSHOT"

resolvers +=
  "Scalastyle SBT plugin" at "https://oss.sonatype.org/content/repositories/releases"


libraryDependencies += "org.typelevel" %% "cats-core" % "2.0.0"
libraryDependencies += "com.typesafe" % "config" % "1.4.0"
libraryDependencies += "org.scalamock" %% "scalamock" % "4.4.0" % Test
libraryDependencies += "org.scalatest" %% "scalatest" % "3.1.0" % Test