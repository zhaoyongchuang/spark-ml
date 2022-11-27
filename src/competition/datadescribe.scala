package competition

import org.apache.spark.sql.{DataFrame, SparkSession}

object datadescribe {
  def main(args: Array[String]): Unit = {
    //创建spark环境
    val spark: SparkSession = SparkSession
      .builder()
      .appName("spark")
      .master("local")
      .getOrCreate()
    val data: DataFrame = spark.read
      .format("csv")
      .option("header", "true")
      .load("src/competition/datas/train.csv")
    data.printSchema()
    data.describe().show()
  }
}
