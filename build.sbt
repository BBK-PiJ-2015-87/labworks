name := "labworks"

version := "1.0"

scalaVersion := "2.12.2"

libraryDependencies := Seq(
  "com.typesafe.akka" %% "akka-http" % "10.0.7",
  "org.postgresql" % "postgresql" % "42.1.1"
)

lazy val commonSettings = Seq(
  test in assembly := {}
)

flywayUrl := "jdbc:postgresql://localhost:5432/laboratory"
flywayUser := "postgres"
flywayPassword := "password"
