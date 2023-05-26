package scala.rdd_stu

object rdd2_stu {
  def main(args: Array[String]): Unit = {
    val conf = new org.apache.spark.SparkConf().setAppName("wordcount").setMaster("local[*]")
    val sc = new org.apache.spark.SparkContext(conf)
    sc.textFile("src/scala/rdd_stu/phone.csv").flatMap(_.split(","))
      .filter(_.startsWith("139"))
      .filter(_.length == 11)
      .foreach(println)




  }
}
