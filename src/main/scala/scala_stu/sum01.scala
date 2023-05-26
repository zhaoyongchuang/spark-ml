package scala

object sum01 {
  def main(args: Array[String]): Unit = {
    def mysum(a:Array[Int]):Int={
      var sum = 0
      for (i <- a){
        sum += i
      }
      sum
    }
    def mysum1(list1:Int*): Unit = {
      var sum = 0
      for (i <- list1){
        sum += i
      }
      sum
    }
    println(mysum1(1,2,3,4,5,6,7,8,9,10))
    println(mysum(Array(1,2,3,4,5,6,7,8,9,10)))




  }
}
