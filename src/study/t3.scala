package study

import org.apache.spark.{SparkConf, SparkContext}

object t3 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("first spark app(scala)")
      .setMaster("local[1]");

    new SparkContext(conf)
      .parallelize(List(1, 2, 3, 4, 5, 6))
      .map(x => x * x)
      .filter(_ > 10)
      .collect()
      .foreach(println);
  }
}
