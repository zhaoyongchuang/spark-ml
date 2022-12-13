package qinjiao

import org.apache.spark.{SparkConf, SparkContext}

object rdd_study {
  def main(args: Array[String]): Unit = {
    val sc :SparkContext = new SparkContext(new SparkConf().setAppName("rdd").setMaster("local[*]"))
    val rdd1 = sc.parallelize(List(1,2,3))
    val rdd2 = rdd1.map(_ *2)
    val rdd3 = sc.parallelize(Array("a b","c b","a s"))
    val rdd4 = rdd3.flatMap(_.split(" "))
    rdd4.foreach(println)



    // filter 算子可以根据条件把集合中的数据过滤出来
    val rdd5 = sc.parallelize(List(1,2,3,4))
    rdd5.filter(_%2==0).collect.foreach(println)
  }
}
