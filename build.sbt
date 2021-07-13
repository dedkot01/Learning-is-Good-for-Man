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
