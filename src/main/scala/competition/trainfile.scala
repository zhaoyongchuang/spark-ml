package competition

import org.apache.spark.ml
import org.apache.spark.ml.classification.{NaiveBayes, NaiveBayesModel}
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}

object trainfile {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession
      .builder()
      .appName("spark")
      .master("local")
      .getOrCreate()
    import spark.implicits._
    val data: DataFrame = spark.read
      .format("csv")
      .option("header", "true")
      .load("src/competition/datas/train.csv")
    data.printSchema()
    //    data.show()
    val data1: DataFrame = data.map(row => {
      val income: Double = row.getAs[String]("income").toDouble
      val age: Double = row.getAs[String]("age").toDouble
      val experience_years: Double = row.getAs[String]("experience_years").toDouble
      val is_married: Double = row.getAs[String]("is_married").toDouble
      val city: Double = row.getAs[String]("city").toDouble
      val region: Double = row.getAs[String]("region").toDouble
      val current_job_years: Double = row.getAs[String]("current_job_years").toDouble
      val house_ownership: Double = row.getAs[String]("house_ownership").toDouble
      val car_ownership: Double = row.getAs[String]("car_ownership").toDouble
      val profession: Double = row.getAs[String]("profession").toDouble
      val label: Double = row.getAs[String]("label").toDouble
      //特征值
      val array: Array[Double] = Array(income, age, experience_years, is_married, city, region, current_job_years,
        house_ownership, car_ownership, profession)
      //将特征值转成向量
      val features: ml.linalg.Vector = Vectors.dense(array)
      (label, features)
    }).toDF("label", "features")
    //加载模型
    data1.show()
    val split: Array[Dataset[Row]] = data1.randomSplit(Array(0.8, 0.2))
    val trainData: Dataset[Row] = split(0)
    val testData: Dataset[Row] = split(1)
    val lr: NaiveBayes = new NaiveBayes()
    val model: NaiveBayesModel = lr.fit(trainData)
    val resultDF: DataFrame = model.transform(testData)
    resultDF.show(100, false)
    model
      .write
      .overwrite()
      .save("src/competition/model")
    val prediction = model.transform(testData)
    val evalutor = new MulticlassClassificationEvaluator()
      .setLabelCol("label")
      .setPredictionCol("prediction")
      .setMetricName("accuracy")
    val accuracy = evalutor.evaluate(prediction)
    println(accuracy)
    //    val falsePositiveRate = trainingSummary.weightedFalsePositiveRate
    //    val truePositiveRate = trainingSummary.weightedTruePositiveRate
    //    val fMeasure = trainingSummary.weightedFMeasure
    //    val precision = trainingSummary.weightedPrecision
    //    val recall = trainingSummary.weightedRecall
    //    println(s"Accuracy: $accuracy\nFPR: $falsePositiveRate\nTPR: $truePositiveRate\n" +
    //      s"F-measure: $fMeasure\nPrecision: $precision\nRecall: $recall")
    spark.stop()
  }
}
