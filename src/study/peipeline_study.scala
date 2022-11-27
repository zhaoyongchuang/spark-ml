package study

import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.ml.feature.{HashingTF, Tokenizer}
import org.apache.spark.ml.linalg.{Vector => mlV}
import org.apache.spark.sql.{Row, SparkSession}

// pipeline 中包含tokenizer，hashingTF 和lr
object peipeline_study {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession
      .builder()
      .appName("myspark")
      .master("local")
      .getOrCreate()
    val training = spark.createDataFrame(Seq(
      (0L, "a b c d", 1.0),
      (1L, "b c f f", 0.0),
      (2L, "d f y r", 1.0),
      (3L, "a s d g", 0.0)
    )).toDF("id", "text", "label")
    //    println(training)
    val tokenizer = new Tokenizer()
      .setInputCol("text")
      .setOutputCol("word")

    val hashingTF = new HashingTF() // 特征向量，1000个哈希桶
      .setNumFeatures(1000)
      .setInputCol(tokenizer.getOutputCol)
      .setOutputCol("features")

    val lr = new LogisticRegression() // 模型
      .setMaxIter(10)
      .setRegParam(0.01)
    // pipeline 本质上是一个Estimator ，在它的fit方法运行之后，产生一个pipelineMode，它是一个Transformer
    val pipeline = new Pipeline()
      .setStages(Array(tokenizer, hashingTF, lr))
    val model = pipeline.fit(training)

    // 测试集
    val test = spark.createDataFrame(
      Seq(
        (4L, "a s d f"),
        (5L, "q w e r"),
        (6L, "z x c v")
      )
    ).toDF("id", "text")

    //
    model.transform(test)
      .select("id", "text", "probability", "prediction")
      .collect()
      .foreach { case Row(id: Long, text: String, probability: mlV, prediction: Double) =>
        println(s"($id,$text)-->probability=$probability,prediction=$prediction")
      }
  }

}
