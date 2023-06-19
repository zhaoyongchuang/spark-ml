package sql_stu

import org.apache.spark.sql.SparkSession

object sql002 {
  def main(args: Array[String]): Unit = {
    val spark = new SparkSession.Builder()
      .appName("sql01")
      .master("local[*]") 
      .getOrCreate()
    import spark.implicits._
    val rdd1 = spark.read.option("multiline",true)
      .json("src/main/scala/sql_stu/name.json")
    val df1 = rdd1.toDF()
    df1.show()
    df1.createOrReplaceTempView("name_score")
    val res1 = spark.sql("select * from name_score where score>100")
    res1.show()
  }
}
