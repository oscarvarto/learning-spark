package com.intersysconsulting.syntax

import string._
import org.apache.spark.sql.{DataFrame, SparkSession}
import scalaz.zio._
import scalaz.syntax.show._
import scalaz.std.anyVal.booleanInstance

object sparksession {

  // See https://leanpub.com/fpmortals/read#leanpub-auto-functionality
  implicit final class CsvOps(private val ss: SparkSession) extends AnyVal {
    // Inspired on Spark-The-Definitive-Guide, chapter 6.
    private def unsafeReadCsv(path: String,
                              header: Boolean,
                              inferSchema: Boolean): DataFrame =
      ss.read.format("csv")
        .option("header", header.shows)
        .option("inferSchema", inferSchema.shows)
        .load(path)

    def readCsv(path: String,
                header: Boolean = true,
                inferSchema: Boolean = true): IO[ErrorMsg, DataFrame] =
      IO.syncCatch(unsafeReadCsv(path, header, inferSchema)) {
        case e : Exception => e.getMessage.error
      }
  }

}
