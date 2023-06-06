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
      var map1: Map[String, Int] = Map()
      map1+=("tom"->1)
      val tup1 = ("1","2","3")
      println(tup1._1)
      def mysum(list:Int *): Unit = {
        var s = 0;
        for (i<-list){
          s = s+i
        }
        return s
      }
      println(mysum(2,3,4,5,6,6,6,1))


    }
  }

}
