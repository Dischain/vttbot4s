name := "vttbot4s"

version := "1.0"

scalaVersion := "2.12.1"

libraryDependencies ++= Seq(
  "info.mukel" %% "telegrambot4s" % "3.0.14",
  "com.typesafe.play" %% "play-json" % "2.6.7",
  "com.typesafe.play" %% "play-ahc-ws-standalone" % "1.1.6",
  "io.leonard" %% "play-json-traits" % "1.4.4"
)