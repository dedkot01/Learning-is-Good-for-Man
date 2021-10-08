lazy val root = project
  .in(file("."))
  .settings(
    name := "learning-is-good-for-man",
    version := "1.0.0"
  )

lazy val somethingSmall = project
  .in(file("something-small"))
  .settings(
    name := "something-small",
    version := "1.0.0",
    scalaVersion := "2.13.6",
    libraryDependencies ++= Seq(
      "com.softwaremill.quicklens" %% "quicklens" % "1.7.4"
    )
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

lazy val simpleFs2Kafka = project
  .in(file("simple-fs2-kafka"))
  .settings(
    name := "simple-fs2-kafka",
    version := "1.0.0",
    scalaVersion := "2.13.6",
    libraryDependencies ++= Seq("com.github.fd4s" %% "fs2-kafka" % "1.7.0")
  )

lazy val simpleKafkaClients = project
  .in(file("simple-kafka-clients"))
  .settings(
    name := "simple-kafka-clients",
    version := "1.0.0",
    scalaVersion := "2.13.6",
    libraryDependencies ++= Seq(
      "org.apache.kafka"    % "kafka-clients" % "2.8.0",
      "com.sksamuel.avro4s" %% "avro4s-kafka" % "4.0.8"
    )
  )

lazy val HttpServerKafka = project
  .in(file("http-server-kafka"))
  .settings(
    name := "http-server-kafka",
    version := "1.0.0",
    scalaVersion := "2.13.6",
    libraryDependencies ++= Seq(
      "org.http4s"          %% "http4s-blaze-server" % http4sVersion,
      "org.http4s"          %% "http4s-circe"        % http4sVersion,
      "org.http4s"          %% "http4s-dsl"          % http4sVersion,
      "io.circe"            %% "circe-generic"       % "0.14.1",
      "org.apache.kafka"    % "kafka-clients"        % "2.8.0",
      "com.sksamuel.avro4s" %% "avro4s-kafka"        % "4.0.8",
      "com.typesafe"        % "config"               % "1.4.1"
    )
  )

lazy val SimpleCassandraClients = project
  .in(file("simple-cassandra-clients"))
  .settings(
    name := "simple-cassandra-clients",
    version := "1.0.0",
    scalaVersion := "2.12.14",
    libraryDependencies ++= Seq(
      "org.apache.flink" %% "flink-clients"             % "1.13.2",
      "org.apache.flink" %% "flink-streaming-scala"     % "1.13.2",
      "org.apache.flink" %% "flink-connector-cassandra" % "1.13.2"
    )
  )
