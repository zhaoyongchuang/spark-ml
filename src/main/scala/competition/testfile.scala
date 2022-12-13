package competition

import org.apache.spark.ml
import org.apache.spark.ml.classification.NaiveBayesModel
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.sql.{DataFrame, SparkSession}

object testfile {
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
      .load("src/competition/datas/test.csv")
    data.printSchema()
    //    data.show()
    val data1: DataFrame = data.map(row => {
      //      val id: String = row.getAs[String]("id").toString
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

      val array: Array[Double] = Array(income, age, experience_years, is_married, city, region, current_job_years,
        house_ownership, car_ownership, profession)
      val features: ml.linalg.Vector = Vectors.dense(array)
      Tuple1(features)
    }).toDF("features")
    //加载模型

    val model: NaiveBayesModel = NaiveBayesModel.load("src/competition/model")
    val proData: DataFrame = model.transform(data1)
    proData.show()
  }
}
