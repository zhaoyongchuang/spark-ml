package database

import org.apache.spark.sql.SparkSession

import java.util.Properties

object
conn_database {
  def main(args: Array[String]): Unit = {
    val spark = new SparkSession.Builder()
      .appName("connect database")
      .master("local[*]")
      .getOrCreate()
    import spark.implicits._
//    连接mysql5.7数据库
    val df1 = spark.read
      .format("jdbc")
      .option("url","jdbc:mysql://192.168.222.188:3306/Car?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false")
      .option("driver","com.mysql.jdbc.Driver")
      .option("dbtable","message")
      .option("user","root")
      .option("password","123456")
      .load()
    df1.show()
    val df2 = df1.toDF()
    val par = new Properties()
//    val rdd1 = spark.sparkContext.parallelize()
    par.setProperty("user","root")
    par.setProperty("password","123456")
    par.setProperty("driver","com.mysql.jdbc.Driver")
    df2.write
      .mode("append")
      .jdbc("jdbc:mysql://192.168.222.188/Car?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false","message",par)









  }
}
