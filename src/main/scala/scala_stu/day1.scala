package scala

object day1 {
  def main(args: Array[String]): Unit = {
    //    val abc = Array(1,2,3,4,5)
    //    for( a <- 0  to 5){
    //      println(a)
    //    }
    //    for (a <- abc){
    //      println(a)
    //    }
    //    出租车计费问题
//    val s: Int = 20
//    if (s <= 1) {
//      println("收费" + 2)
//    }
//    else if (s > 1 && s <= 5) {
//      println("收费" + 3)
//    }
//    else {
//      println("收费" + 3)
//    }
    var sum = 0
//    for(a <- 1 to 100){
//      sum += a
//    }
//    println(sum)
    var a = 1
    while (a<=100){
      sum += a
      a+=1
    }
    println(sum)



    def add1(a:Int,b:Int):Int={return a+b}
    def add2(a:Int,b:Int):Int={a+b}
    def add3(a:Int,b:Int)={a+b}
    def add4(a:Int)(b:Int)=a+b

    def f() = 8
    println(f)
  }
}