package study

import org.apache.spark.{SparkConf, SparkContext};

object wordcount {
  def main(args: Array[String]): Unit = {
    //创建spark环境
    val conf = new SparkConf().setMaster("local").setAppName("wc")
    val sc = new SparkContext(conf)
    val lines = sc.textFile("src/main/scala/study/data/word.csv")
    val words = lines.flatMap(line => line.split(","))
    val count = words.map(word => (word, 1)).reduceByKey { case (x, y) => x + y }
    // 转换成键值对，且相同键值对组合计数
    // lambda x:(x,1)
    // lambda x,y:x+y

//    val count = words.map(word => (word, 1)).reduceByKey(_ + _)
//     按照values排序，取前10个
    val sort_value = count.sortBy(_._2, false).take(10)
    println(sort_value.toBuffer)

  }
}

