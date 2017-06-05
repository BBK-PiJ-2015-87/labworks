name := "labworks"

version := "1.0"

scalaVersion := "2.12.2"

libraryDependencies := Seq(
  "com.typesafe.akka" %% "akka-http" % "10.0.7",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.0.3",
  "org.postgresql" % "postgresql" % "9.4.1211",
//  "org.postgresql" % "postgresql" % "42.1.1",
  "com.typesafe.slick" %% "slick" % "3.2.0",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.2.0"
)

lazy val commonSettings = Seq(
  test in assembly := {}
)

flywayUrl := "jdbc:postgresql://localhost:5432/laboratory"
flywayUser := "postgres"
flywayPassword := "password"
