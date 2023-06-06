package sql_stu

import org.apache.spark.sql.SparkSession

object df_filter {
  def main(args: Array[String]): Unit = {
    val spark = new SparkSession.Builder()
      .appName("df filter")
      .master("local[*]")
      .getOrCreate()
    import spark.implicits._
    val rdd1 = spark.sparkContext.parallelize(
      List()
    )




  }
}
