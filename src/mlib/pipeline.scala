package mlib

import org.apache.spark.ml.classification.NaiveBayes
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.sql.SparkSession

object pipeline {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder
      .master("local")
      .appName("naivebayesExample")
      .getOrCreate()
    val data = spark.read.format("libsvm").load("D:\\spark-master\\data\\mllib\\sample_libsvm_data.txt")
    val Array(trainingData, testData) = data.randomSplit(Array(0.7, 0.3), seed = 1234L)
    val model = new NaiveBayes().fit(trainingData)
    val predictions = model.transform(testData)
    predictions.show()
    val evaluator = new MulticlassClassificationEvaluator()
      .setLabelCol("label")
      .setPredictionCol("prediction")
      .setMetricName("accuracy")

    val accuracy = evaluator.evaluate(predictions)
    println(s"Test set accuracy  = $accuracy")


    spark.stop()
  }
}