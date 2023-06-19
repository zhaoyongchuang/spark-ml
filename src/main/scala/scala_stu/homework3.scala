package scala

import org.apache.spark.{SparkConf, SparkContext}

object homework3 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("homework3")
      .setMaster("local[*]")
    val sc = new SparkContext(conf)
    val rdd1 = sc.textFile("src/main/scala/scala_stu/score.txt")

// 统计赵勇闯的平均分
    val rddzyc = rdd1.filter(x=>x.contains("zyc"))
    val name_score = rddzyc.map(x=>(x.split(",")(0),x.split(",")(2).toInt))
    name_score.groupByKey().map(x=>(x._1,x._2.sum/x._2.size)).foreach(println(_))

//    统计每门课的平均分
    val rdd_ke_score = rdd1.map(x=>(x.split(",")(1),x.split(",")(2).toInt))
    rdd_ke_score.groupByKey().map(x=>(x._1,x._2.sum/x._2.size)).foreach(println(_))

//    每个人选了哪几门课
    val rdd_ke = rdd1.map(x=>(x.split(",")(0),x.split(",")(1)))
    rdd_ke.groupByKey().map(x=>(x._1,x._2.toList)).foreach(println(_))
//    统计有几门课
    println(rdd1.map(x=>(x.split(",")(1),x.split(",")(2).toInt)).groupByKey().count())






  }
}
