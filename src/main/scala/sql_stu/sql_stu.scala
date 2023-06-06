package sql_stu

import org.apache.spark.{SparkConf, SparkContext}

object sql_stu {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("sparksql_stu")
      .setMaster("local[*]")
    val sc = new SparkContext(conf)
    sc.parallelize(List(12,34))



  }

}
