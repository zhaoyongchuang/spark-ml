package sql_stu

import org.apache.spark.sql.SparkSession

object read_csv_stu {
  def main(args: Array[String]): Unit = {
    val spark = new SparkSession.Builder()
      .appName("read csv")
      .master("local[*]")
      .getOrCreate()
    import spark.implicits._
    val rdd1 = spark.read
//      .option("header",value = true)
      .csv("src/main/scala/sql_stu/score.csv")

    val df1 = rdd1.toDF("name","java","python","scala")
//    有没有header

    df1.show()
    df1.createOrReplaceTempView("student")
    val res1 = spark.sql(
      "select count(java) as java_n,count(python) as python_n,count(scala) as scala_n from student"
    )
    res1.show()

    val res2 = spark.sql("select name,java from student").show()
    df1.filter(df1("java")>80).show()
    df1.sort(df1("java")).show()
  }
}
