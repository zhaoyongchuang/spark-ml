package scala.rdd_stu

object fenzu_stu {
  def main(args: Array[String]): Unit = {
    val conf = new org.apache.spark.SparkConf().setAppName("wordcount").setMaster("local[*]")
    val sc = new org.apache.spark.SparkContext(conf)
//    parallelize
    val rdd1 = sc.parallelize(List("hello", "world", "hello", "scala"))
    val rdd2 = rdd1.map((_, 1))
//    val rdd3 = rdd2.reduceByKey(_ + _)
    val rdd3 =rdd2.groupByKey()
    rdd3.foreach(println)
//    实现wordcount效果使用groupByKey
//    (world,CompactBuffer(1))
//    (hello,CompactBuffer(1, 1))
//    (scala,CompactBuffer(1))
    val rdd4 = rdd3.map(x => (x._1, x._2.sum))
    rdd4.foreach(println)


  }
}
