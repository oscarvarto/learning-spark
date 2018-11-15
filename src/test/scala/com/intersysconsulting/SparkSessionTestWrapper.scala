package com.intersysconsulting

import org.apache.spark.sql.SparkSession
import scalaz.zio._

trait SparkSessionTestWrapper {

  lazy val spark: SparkSession = {
    SparkSession.builder().master("local").appName("spark session").getOrCreate()
  }

  object muteRTS extends RTS {
    override def defaultHandler: List[Throwable] => IO[Nothing, Unit] = _ => IO.unit
  }

  implicit final class RunSyntax[E, A](private val io: IO[E, A]) {
    def unsafeRun: A = muteRTS.unsafeRun(io)
    def unsafeRunSync: ExitResult[E, A] = muteRTS.unsafeRunSync(io)
  }
}
