package scala.fenqu

import org.apache.spark.Partitioner

object mypart {
  def main(args: Array[String]): Unit = {
//    自定义分区
    val conf = new org.apache.spark.SparkConf()
      .setAppName("mypart")
      .setMaster("local[*]")
    val sc = new org.apache.spark.SparkContext(conf)
    var a1 = Array(1,2,3,4,5,6,7,8,9,10,11,12,13,14)
    var rdd1 = sc.parallelize(a1)
    rdd1.map((_,1))
      .partitionBy(new mypartioner(2))
      .saveAsTextFile("src\\scala\\fenqu\\mypart_def\\")
  }
}
class mypartioner(num:Int) extends Partitioner{
  override def numPartitions: Int = num

  override def getPartition(key: Any): Int = {
    val score = key.asInstanceOf[Int]
    if (score<10){
      0
    }else{
      1
    }

  }
}

//class mypartioner2(num:Int) extends Partitioner{
//  override def numPartitions: Int = num
//
//  override def getPartition(key: Any): Int = {
//
//  }
//}