package scala.fenqu

import org.apache.spark.RangePartitioner

object range_part {
  def main(args: Array[String]): Unit = {
//    范围分区，分区范围range是一个rdd
    val conf = new org.apache.spark.SparkConf()
      .setAppName("range_part")
      .setMaster("local[*]")
    val sc = new org.apache.spark.SparkContext(conf)
    var a1 = Array(1,2,3,4,5,6,7,8,9,10,11,12,13,14)
    var rdd1 = sc.parallelize(a1)
    var range1 = sc.parallelize(List(4,6,7)).map((_,1))
    var rdd2 = sc.parallelize(a1).map((_,1))
      .partitionBy(new RangePartitioner(3,range1))
  .saveAsTextFile("src\\scala\\fenqu\\range_part\\")


// seq 函数用途：val source1=spark.sparkContext.parallelize(
//    Seq(Person("zs",19),Person("ls",21),Person("ww",22))
//    )
//    其中的seq 的功能是将一个序列转换成另一个序列，比如上面的例子中，
    //    将一个Person的序列转换成了一个String的序列，这个转换的过程是通过一个函数来实现的，
    //    这个函数就是seq函数的参数，这个函数的参数是一个Person对象，返回值是一个String对象，
    //    这个函数的返回值就是一个String的序列，这个String的序列就是seq函数的返回值。



  }

}
