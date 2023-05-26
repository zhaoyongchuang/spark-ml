package scala

object partition_stu {
//
def main(args: Array[String]): Unit = {

//  grouped study
  val list1 = List(1,2,3,4,5).grouped(2)
  for (elem <- list1) {
    println(elem)
  }
  val (left, righy) = List(1, 2, 3, 4, 5).partition(_ % 2 == 1) // 真值放在左区

  println(left)
  println(righy)
  for (elem <- left) {
    println(elem)
  }
  for (elem <- righy) {
    println(elem)
  }
  val (left1, righy1) = List(1, 2, 3, 4, 5).partition(f1) // 真值放在左区
  println(left1)




}

  def f1(int: Int): Boolean = {
    int % 2 == 1
  }
}
