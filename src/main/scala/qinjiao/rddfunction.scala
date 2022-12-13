package qinjiao

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object rddfunction {
  def main(args: Array[String]): Unit = {
    val sc :SparkContext = new SparkContext(new SparkConf().setAppName("rdd").setMaster("local[*]"))
    val empRDD: RDD[(Int, String)] = sc.parallelize(
      Seq((1001, "zhangsan"), (1002, "lisi"), (1003, "wangwu"), (1004, "zhangliu"))
    )
    // 部门集合:RDD[(部门编号, 部门名称)]
    val deptRDD: RDD[(Int, String)] = sc.parallelize(
      Seq((1001, "销售部"), (1002, "技术部"))
    )

    // 需求:求出员工所属的部门名称
    // RDD的join直接按照key进行join
    val result1: RDD[(Int, (String, String))] = empRDD.join(deptRDD)
    result1.foreach(println)
    println("============================")
    val result2: RDD[(Int, (String, Option[String]))] = empRDD.leftOuterJoin(deptRDD)
    result2.foreach(println)

    // 关闭资源
    val result: RDD[(String, Int)] = sc.textFile("???")
      .flatMap(_.split(" "))
      .map((_, 1))
      .reduceByKey(_ + _)
    // 打印统计数据，和后面排序处理数据对比
    result.foreach(println)
    println("============sortBy:适合大数据量排序====================")
    val sortedResult1: RDD[(String, Int)] = result.sortBy(_._2, false) //false表示逆序
    sortedResult1.take(3).foreach(println)
    println("============sortByKey:适合大数据量排序====================")
    //val sortedResult2: RDD[(Int, String)] = result.map(t=>(t._2,t._1)).sortByKey(false)
    val sortedResult2: RDD[(Int, String)] = result.map(_.swap).sortByKey(false) //swap表示交换位置
    sortedResult2.take(3).foreach(println)
    println("============top:适合小数据量排序====================")
    val sortedResult3: Array[(String, Int)] = result.top(3)(Ordering.by(_._2)) //注意:top本身就是取最大的前n个
    sortedResult3.foreach(println)

    //5.关闭资源
    sc.stop()
  }

}
