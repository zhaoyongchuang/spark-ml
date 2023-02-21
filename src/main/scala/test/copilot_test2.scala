package test

import org.apache.spark.{SparkConf, SparkContext}

object copilot_test2 {
  def main(args: Array[String]): Unit = {
//    要求使用Sparkcore对数据进行分割处理获取业务需求所使用到的数据，根据不同用户在基站停留的时长数据统计出用户与停留地点的关系
//    项目实现
//    1. 想要获取用户家，公司位置,可以通过用户在某一地点的时间长短进行判断（关联基站）
//    2. 两个位置有多个基站
//    3. 计算每个基站停留时间
//    4. 找出停留时间最长的2个
//
//    5. 获取基站位置，基站位置由经度纬度构成
//    6. 根据排序，前两个常去的位置则可能是家和公司
    val conf = new SparkConf().setAppName("copilot_test2").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val rdd1 = sc.textFile("D:\\copilot\\copilot_test2\\data\\user_location.txt")
    val rdd2 = sc.textFile("D:\\copilot\\copilot_test2\\data\\station_location.txt")
    val rdd3 = rdd1.map(_.split(",")).map(x => (x(0), x(1), x(2).toLong))
    val rdd4 = rdd2.map(_.split(",")).map(x => (x(0), (x(1), x(2))))
    val rdd5 = rdd3.map(x => (x._2, (x._1, x._3)))
    val rdd6 = rdd5.join(rdd4).map(x => (x._2._1._1, (x._1, x._2._1._2, x._2._2._1, x._2._2._2)))
    val rdd7 = rdd6.map(x => (x._1, (x._2._1, x._2._2, x._2._3, x._2._4))).groupByKey()
    val rdd8 = rdd7.map(x => {
      val list = x._2.toList
      val list1 = list.sortBy(_._2).reverse
      val list2 = list1.take(2)
      (x._1, list2)
    })
    rdd8.foreach(println)
    sc.stop()


  }

}
