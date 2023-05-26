package scala

object array_stu {
  def main(args: Array[String]): Unit = {
    //    val mylist = Array(1, 2, 3, 4)
    //    val arr:Array[Int] = new Array[Int](3)
    //    arr(0) = 1
    //    arr(1) = 2
    //    arr(2) = 3
    var a = Array.ofDim[Int](3, 3)
    for (i <- 0 to 2) {
      for (j <- 0 to 2) {
        a(i)(j) = i + j
      }

    }
    for (i <- 0 to 2) {
      for (j <- 0 to 2) {
        print(a(i)(j) + " ")
      }
      println()
    }
  }
}
