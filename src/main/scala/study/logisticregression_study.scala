package study

import org.apache.spark.sql.SparkSession

object logisticregression_study {
  def main(args: Array[String]): Unit = {
    // logistics
    val spark: SparkSession = SparkSession
      .builder()
      .appName("spark ml lr")
      .master("local")
      .getOrCreate()


  }

}
