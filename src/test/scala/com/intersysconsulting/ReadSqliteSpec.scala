package com.intersysconsulting
import org.scalatest.{FunSpec, Matchers}

class ReadSqliteSpec extends FunSpec
  with SparkSessionTestWrapper
  with Matchers {

  val driver: String = "org.sqlite.JDBC"
  val path: String =
    getClass.getResource("/chinook-database/Chinook_Sqlite_AutoIncrementPKs.sqlite").getPath

  val url: String = s"jdbc:sqlite:/${path}"

  it("should read the 17 names of the tracks in a DataFrame") {
    val pushdownQuery =
      """( select name
        |    from track
        |   where albumid = 193
        |order by trackid)
      """.stripMargin

    val dbDataFrame =
      spark.read
        .format("jdbc")
        .option("url", url)
        .option("dbtable", pushdownQuery)
        .option("driver", driver)
        .load()

    dbDataFrame.count shouldBe 17L
    dbDataFrame.collect.flatMap(_.toSeq) shouldBe Vector(
      "The Power Of Equality",
      "If You Have To Ask",
      "Breaking The Girl",
      "Funky Monks",
      "Suck My Kiss",
      "I Could Have Lied",
      "Mellowship Slinky In B Major",
      "The Righteous & The Wicked",
      "Give It Away",
      "Blood Sugar Sex Magik",
      "Under The Bridge",
      "Naked In The Rain",
      "Apache Rose Peacock",
      "The Greeting Song",
      "My Lovely Man",
      "Sir Psycho Sexy",
      "They're Red Hot"
    )
  }
}
