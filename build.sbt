lazy val root = project
  .in(file("."))
  .settings(
    name := "learning-is-good-for-man",
    version := "1.0.0"
  )

lazy val functionalProgrammingInScala = project
  .in(file("functional-programming-in-scala"))
  .settings(
    name := "functional-programming-in-scala",
    version := "1.0.0",
    scalaVersion := "3.0.0",
    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"
  )

val http4sVersion = "1.0.0-M23"

lazy val simpleHttp4sServer = project
  .in(file("simple-http4s-server"))
  .settings(
    name := "simple-http4s-server",
    version := "1.0.0",
    scalaVersion := "2.13.6",
    libraryDependencies ++= Seq(
      "org.http4s" %% "http4s-blaze-server" % http4sVersion,
      "org.http4s" %% "http4s-circe"        % http4sVersion,
      "org.http4s" %% "http4s-dsl"          % http4sVersion,
      "io.circe"   %% "circe-generic"       % "0.14.1"
    )
  )
