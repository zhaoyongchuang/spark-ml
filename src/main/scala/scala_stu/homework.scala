package scala



import scala.io.StdIn
import scala.util.Random

object homework {
  def main(args: Array[String]): Unit = {
    val num = Random.nextInt(100)

    val input_num = StdIn.readInt()
    while (input_num != num) {
      if (input_num > num) {
        println("猜大了")
      } else {
        println("猜小了")
      }


    }
  }

}
