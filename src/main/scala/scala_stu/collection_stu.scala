package scala

import scala.collection.mutable.ListBuffer

object collection_stu {
  def main(args: Array[String]): Unit = {
//    可变集合，与不可变函数
    val list1:List[Int] = List(1,2,3,4)
    val list2=List(1,2,3,4)
//    println(list1.+:(5))  // 模拟添加元素
//    println(list1)
//    for(i <- list1){
//      println(i)
//    }
    list2.foreach(println)
    list1.foreach(j=>println(j))
//    列表的buffer用法
    var listBuffer:ListBuffer[Int] = ListBuffer(1,2,3,4)
    listBuffer.append(5)
    listBuffer+=6
    listBuffer-=1


  }
}
