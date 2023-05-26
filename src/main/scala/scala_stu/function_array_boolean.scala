package scala

import scala.collection.mutable.ListBuffer

object function_array_boolean {
  def main(args: Array[String]): Unit = {

  }

  def func1(array: Array[Int],fp:Int=>Boolean)={
    var lst  = ListBuffer[Int]()
    for (i<-array){
      if (fp(i)){
        lst.append(i)
      }

    }
    lst
  }
  def myf(n:Int)={
    n%2==0
  }
  func1(Array(1,2,3,4,5,6,7,8,9,10),myf).foreach(println(_) )


}
