package anhui

import org.apache.spark.ml.classification.{NaiveBayes, NaiveBayesModel}
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}

object sparksql1 {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName("sql")
      .master("local")
      .getOrCreate()
    val data: DataFrame = spark.read
      .format("csv")
      .option("header", "true")
      .load("src/anhui/comments_with_label.csv")
    val split: Array[Dataset[Row]] = data.randomSplit(Array(0.8, 0.2))
    val trainData: Dataset[Row] = split(0)
    val testData: Dataset[Row] = split(1)
    //    println("======训练集数据条数")
    //    println(trainData.count())
    //    println("======测试集数据条数")
    //    println(testData.count())
    val by: NaiveBayes = new NaiveBayes()
    val model: NaiveBayesModel = by.fit(trainData)
    val resultDF: DataFrame = model.transform(testData)
    resultDF.show(100, false)
    //    val trainingSummary = model.summary
    //    val accuracy = trainingSummary.accuracy


  }

}
