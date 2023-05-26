package scala

object scan_stu {
  def main(args: Array[String]): Unit = {
//    scan function study
    println(List(1,2,3,4,5).scan(0)(_+_)) // List(0,1,3,6,10,15)
//
    println(List(1,2,3,4,5).scan(1)(_*_)) // List(1,1,2,6,24,120)






  }
}
