package scala

object sortWith_stu {
  def main(args: Array[String]): Unit = {
//    sortWith function study
    println(List(1,2,3,4,5).sortWith(_>_)) // List(5,4,3,2,1)
    println(List(1,2,3,4,5).sortWith(_<_)) // List(1,2,3,4,5)

  }
}
