package scala_stu

object fuxi {
  def main(args: Array[String]): Unit = {
    val b = 1+(2);
    val a = b.toInt
    println(b)
    println(a)
   // 自定义分区，对通信录按a-z分成4个区





  }
//  class mypartitioner(n:Int) extends Partitioner{
//    override def numPartitions: Int = n

//    override def getPartition(key: Any): Int = {
//      val k:Char = key.toString.charAt(0)
//      if (k<="n"){
//        if(k<='g') {
//          return 0
//        }
//        return 1
//      }
//      else{
//        if (k<='t')
//          return 2
//        else
//          return 3
//      }
//    }
//  }

}
