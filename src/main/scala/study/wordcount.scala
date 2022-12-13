package study

import org.apache.spark.{SparkConf, SparkContext};

object wordcount {
  def main(args: Array[String]): Unit = {
    //创建spark环境
    val conf = new SparkConf().setMaster("local").setAppName("wc")
    val sc = new SparkContext(conf)
    val lines = sc.textFile("src/study/word.csv")
    val words = lines.flatMap(line => line.split(","))
    val count = words.map(word => (word, 1)).reduceByKey { case (x, y) => x + y }
    // 转换成键值对，且相同键值对组合计数
    // lambda x:(x,1)
    // lambda x,y:x+y
    println(count.collect().mkString("\n"))

  }
}

