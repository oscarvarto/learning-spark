import sbt._

object Dependencies {
  lazy val sparkDaria           = "com.github.mrpowers"   %% "spark-daria"            % "0.26.1"
  lazy val sparkFastTests       = "com.github.mrpowers"   %% "spark-fast-tests"       % "0.17.1"
  lazy val scalaTest            = "org.scalatest"         %% "scalatest"              % "3.2.0-SNAP10"
  lazy val sqliteJdbc           = "org.xerial"            %  "sqlite-jdbc"            % "3.25.2"

  lazy val scalazCore           = "org.scalaz"            %% "scalaz-core"            % "7.2.27"
  lazy val scalazZio            = "org.scalaz"            %% "scalaz-zio"             % "0.3.2"
  lazy val refined              = "eu.timepit"            %% "refined"                % "0.9.2"
  lazy val refinedScalaz        = "eu.timepit"            %% "refined-scalaz"         % "0.9.2"
  lazy val singletonOps         = "eu.timepit"            %% "singleton-ops"          % "0.3.1"
  lazy val spire                = "org.typelevel"         %% "spire"                  % "0.16.0"
  lazy val scalaCheck           = "org.scalacheck"        %% "scalacheck"             % "1.14.0"
  lazy val shapeless            = "com.chuusai"           %% "shapeless"              % "2.3.3"
  lazy val avro4s               = "com.sksamuel.avro4s"   %% "avro4s-core"            % "2.0.2"


  // available for Scala 2.11, 2.12
  lazy val fs2Core              = "co.fs2"                %% "fs2-core"               % "1.0.0" // For cats 1.4.0 and cats-effect 1.0
  // optional I/O library
  lazy val fs2Io                = "co.fs2"                %% "fs2-io"                 % "1.0.0"
  // optional reactive streams interop
  lazy val fs2ReactiveStreams   = "co.fs2"                %% "fs2-reactive-streams"   % "1.0.0"
  // optional experimental library
  lazy val fs2Experimental      = "co.fs2"                %% "fs2-experimental"       % "1.0.0"

  lazy val circeFs2             = "io.circe"              %% "circe-fs2"              % "0.10.0"

  lazy val catsCore             = "org.typelevel"         %% "cats-core"              % "1.4.0"
  lazy val catsFree             = "org.typelevel"         %% "cats-free"              % "1.4.0"
  lazy val catsEffect           = "org.typelevel"         %% "cats-effect"            % "1.1.0-M1"
  lazy val fs2Kafka             = "com.ovoenergy"         %% "fs2-kafka"              % "0.16.4"

  lazy val vegas                = "org.vegas-viz"         %% "vegas"                  % "0.3.12-SNAPSHOT"
}
