package scala

import org.apache.spark.{SparkConf, SparkContext}

object homework3 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("homework3")
      .setMaster("local[*]")
    val sc = new SparkContext(conf)
    val rdd1 = sc.textFile("src/main/scala/scala_stu/score.txt")
//    val rdd2 = rdd1.map((x=>x.split(",")(0)),(x=>x.split(",")(1)))
//      .groupByKey()
    for(i<-rdd1){
      println(i)
    }


  }
}
