package scala

object range {
  def main(args: Array[String]): Unit = {
    //    println(1 to 5)
    //    println(1.to(5))
    //    println(1 until(5))//不包含end
    //    println(1 to 5 by 2)
    for (a <- 0 to 5) {
      println(a)
    }

  }
}
