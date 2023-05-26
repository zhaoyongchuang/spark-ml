package scala.rdd_stu

object rdd1_stu {
  def main(args: Array[String]): Unit = {
//
    val conf = new org.apache.spark.SparkConf().setAppName("wordcount").setMaster("local[*]")
    val sc = new org.apache.spark.SparkContext(conf)
    val rdd1 = sc.textFile("file:///D:\\test.txt")

  }
}
