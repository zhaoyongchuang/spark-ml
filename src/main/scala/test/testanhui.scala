package test

import org.apache.spark.ml
import org.apache.spark.ml.classification.{LogisticRegression, LogisticRegressionModel}
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}

object testanhui {
  def main(args: Array[String]): Unit = {
    //创建spark环境
    val spark: SparkSession = SparkSession
      .builder()
      .appName("spark")
      .master("local")
      .getOrCreate()
    import spark.implicits._
    val data: DataFrame = spark.read
      .format("csv")
      .option("header", "true")
      .load("src/test/训练集.csv")
    data.printSchema()
    data.show()
    val data1: DataFrame = data.map(row => {
      val FLOW: Double = row.getAs[String]("FLOW").toDouble
      val FLOW_LAST_ONE: Double = row.getAs[String]("FLOW_LAST_ONE").toDouble
      val FLOW_LAST_TWO: Double = row.getAs[String]("FLOW_LAST_TWO").toDouble
      val MONTH_FEE: Double = row.getAs[String]("MONTH_FEE").toDouble
      val MONTHS_3AVG: Double = row.getAs[String]("MONTHS_3AVG").toDouble
      val BINDEXP_DATE: Double = row.getAs[String]("BINDEXP_DATE").toDouble
      val PHONE_CHANGE: Double = row.getAs[String]("PHONE_CHANGE").toDouble
      val AGE: Double = row.getAs[String]("AGE").toDouble
      val OPEN_DATE: Double = row.getAs[String]("OPEN_DATE").toDouble
      val REMOVE_TAG: String = row.getAs[String]("REMOVE_TAG")
      val label: Double = REMOVE_TAG match {
        case "A" => 1.0
        case _ => 0.0
      }
      //特征值
      val array: Array[Double] = Array(FLOW, FLOW_LAST_ONE, FLOW_LAST_TWO, MONTH_FEE,
        MONTHS_3AVG, BINDEXP_DATE, PHONE_CHANGE, AGE, OPEN_DATE)
      //将特征值转成向量
      val features: ml.linalg.Vector = Vectors.dense(array)
      (label, features)
    }).toDF("label", "features")
    //加载模型
    data1.show()
    val split: Array[Dataset[Row]] = data1.randomSplit(Array(0.8, 0.2))
    val trainData: Dataset[Row] = split(0)
    val testData: Dataset[Row] = split(1)

    val lr: LogisticRegression = new LogisticRegression()
    val model: LogisticRegressionModel = lr.fit(trainData)
    val resultDF: DataFrame = model.transform(testData)

    resultDF.show(20, false)
    //    model
    //      .write
    //      .overwrite()
    //      .save("data/model")
    val trainingSummary = model.summary
    val accuracy = trainingSummary.accuracy
    val falsePositiveRate = trainingSummary.weightedFalsePositiveRate
    val truePositiveRate = trainingSummary.weightedTruePositiveRate
    val fMeasure = trainingSummary.weightedFMeasure
    val precision = trainingSummary.weightedPrecision
    val recall = trainingSummary.weightedRecall
    println(s"Accuracy: $accuracy\nFPR: $falsePositiveRate\nTPR: $truePositiveRate\n" +
      s"F-measure: $fMeasure\nPrecision: $precision\nRecall: $recall")
    spark.stop()

  }
}

