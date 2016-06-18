name := """jts-scala-practice"""

version := "1.0"

scalaVersion := "2.10.4"

resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-json" % "2.3.4",
  "com.typesafe.play.extras" %% "play-geojson" % "1.2.0",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  "com.vividsolutions" % "jts" % "1.13",
  "com.github.tminglei" %% "slick-pg" % "0.8.2"
)

// Uncomment to use Akka
//libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.11"
