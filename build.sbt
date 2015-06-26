name := """jts-tutorial"""

version := "1.0"

scalaVersion := "2.11.6"

// Change this to another test framework if you prefer
libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  "com.vividsolutions" % "jts" % "1.13"
)


// Uncomment to use Akka
//libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.11"

