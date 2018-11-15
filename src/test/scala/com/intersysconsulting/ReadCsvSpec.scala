package com.intersysconsulting

import com.github.mrpowers.spark.fast.tests.DataFrameComparer
import com.intersysconsulting.syntax.sparksession.CsvOps
import com.intersysconsulting.syntax.string.ErrorMsg
import org.apache.spark.sql.DataFrame
import org.scalatest.{FunSpec, Matchers}
import scalaz.zio.IO

class ReadCsvSpec extends FunSpec
  with SparkSessionTestWrapper
  with DataFrameComparer
  with Matchers {

  val retailData: String =
    getClass.getResource("/retail-data/by-day/2010-12-01.csv").getPath

  it("should report an error if csv file does not exist") {
    val result = spark.readCsv("").unsafeRunSync
    result.failed shouldBe true
  }

  it("should load a DataFrame if the csv file exists") {
    val ioDf: IO[ErrorMsg, DataFrame] = spark.readCsv(retailData)
    val df: DataFrame = ioDf.unsafeRun
    df.count() shouldBe 3108L
  }
}
