package sql_stu

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.avg

object sql001 {
  def main(args: Array[String]): Unit = {
    val spark = new SparkSession.Builder()
      .appName("sql01")
      .master("local[*]")
      .getOrCreate()
    import spark.implicits._
    val rdd1 = spark.sparkContext.parallelize(
      List(student001("tom",31),student001("jerry",21),student001("zyc",22),
        student001("za",22),student001("tom",11),student001("za",89))
    )
    val df1 = rdd1.toDF()
    df1.show()
    df1.createOrReplaceTempView("student")
    val res1  = spark.sql(
      "select * from student where age>20"
    )
    res1.show()
    println("========filter")
    df1.filter(df1("age")>22).show()
    println("=========sort")
    df1.sort(df1("age").desc).show()  //降序
    df1.sort(df1("age")).show() //升序
    println("=========multi sort")
    df1.sort(df1("age").desc,df1("name")).show()
//    agg聚合函数
    df1.groupBy(df1("name")).agg(avg(df1("age"))).show()
    df1.select("name","age")
      .groupBy("name")
      .agg(avg("age"))
      .show()







  }

}
case class student001(name:String,age:Int)
