//package scala.fenqu
//
//object hash_part {
//  def main(args: Array[String]): Unit = {
////    var conf = new SparkConf()
////      .setAppName("hash_part")
////      .setMaster("local[*]")
////    val sc = new SparkContext(conf)
////    val a1:ListBuffer[Int] = ListBuffer()
////    for(k<-1 to 100){
////      for(j<-1 to 10){
////        val a = (new Random()).nextInt(100)
////        if (a%4!=0)
////          a1+=a
////      }
////      for(k<-10 to 900){
////        a1+=((new Random).nextInt(40)) + 60
////      }
////
////
////    }
////    var rdd1 = sc.parallelize(a1)
////    var rdd2 = rdd1.map((_,1)).sortByKey()
////      .partitionBy(new org.apache.spark.HashPartitioner(8))
//
//
////      .saveAsTextFile("src\\scala\\fenqu\\hash_part\\")
//
//
//
//
//  }
//
//}
