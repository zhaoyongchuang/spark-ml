//package scala.fenqu
//
//object range_part {
//  def main(args: Array[String]): Unit = {
////    范围分区，分区范围range是一个rdd
//    val conf = new org.apache.spark.SparkConf()
//      .setAppName("range_part")
//      .setMaster("local[*]")
//    val sc = new org.apache.spark.SparkContext(conf)
//    var a1 = Array(1,2,3,4,5,6,7,8,9,10,11,12,13,14)
//    var rdd1 = sc.parallelize(a1)
//    var range1 = sc.parallelize(List(4,6,8)).map((_,1))
////    var rdd2 = sc.parallelize(a1).map((_,1))
////      .partitionBy(new RangePartitioner(3,range1))
////  .saveAsTextFile("src\\scala\\fenqu\\range_part\\")
//
//
//
//
//
//  }
//
//}
