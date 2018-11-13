resolvers += "jitpack" at "https://jitpack.io"

name := "learning-spark"

version := "0.1.0"

scalaVersion := "2.12.7"

sparkVersion := "2.4.0"

sparkComponents ++= Seq("sql")

libraryDependencies ++= Seq(
  "com.github.mrpowers" % "spark-daria" % "v0.26.0",
  "com.github.mrpowers" % "spark-fast-tests" % "v0.16.0" % "test",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test"
)

// test suite settings
fork in Test := true
javaOptions ++= Seq("-Xms512M", "-Xmx2048M", "-XX:MaxPermSize=2048M", "-XX:+CMSClassUnloadingEnabled")
// Show runtime of tests
testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest, "-oD")

// JAR file settings
assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)
// Add the JAR file naming conventions described here: https://github.com/MrPowers/spark-style-guide#jar-files
// You can add the JAR file naming conventions by running the shell script
