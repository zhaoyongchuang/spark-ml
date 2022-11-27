package test

import org.apache.spark.ml
import org.apache.spark.ml.classification.LogisticRegressionModel
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.sql.{DataFrame, SparkSession}

object yuce {
  def main(args: Array[String]): Unit = {
    //创建spark环境
    val spark: SparkSession = SparkSession
      .builder()
      .appName("spark")
      .master("local[4]")
      .config("spark.sql.shuffle.partitions", 1)
      .getOrCreate()
    import spark.implicits._
    val data: DataFrame = spark.read
      .format("csv")
      .option("header", "true")
      .load("src/test/训练集.csv")
    data.printSchema()
    data.show()
    val data1: DataFrame = data.map(row => {
      //      val USER_ID: Double = row.getAs[String]("USER_ID").toDouble
      val FLOW: Double = row.getAs[String]("FLOW").toDouble
      val FLOW_LAST_ONE: Double = row.getAs[String]("FLOW_LAST_ONE").toDouble
      val FLOW_LAST_TWO: Double = row.getAs[String]("FLOW_LAST_TWO").toDouble
      val MONTH_FEE: Double = row.getAs[String]("MONTH_FEE").toDouble
      val MONTHS_3AVG: Double = row.getAs[String]("MONTHS_3AVG").toDouble
      val BINDEXP_DATE: Double = row.getAs[String]("BINDEXP_DATE").toDouble
      val PHONE_CHANGE: Double = row.getAs[String]("PHONE_CHANGE").toDouble
      val AGE: Double = row.getAs[String]("AGE").toDouble
      val OPEN_DATE: Double = row.getAs[String]("OPEN_DATE").toDouble
      //特征值
      val array: Array[Double] = Array(FLOW, FLOW_LAST_ONE, FLOW_LAST_TWO, MONTH_FEE, MONTHS_3AVG, BINDEXP_DATE, PHONE_CHANGE, AGE, OPEN_DATE)
      //将特征值转成向量
      val features: ml.linalg.Vector = Vectors.dense(array)
      Tuple1(features)
    }).toDF("features")
    //加载模型
    val model: LogisticRegressionModel = LogisticRegressionModel.load("data/model")
    val proData: DataFrame = model.transform(data1)
    proData.show()

  }

}
