package scala

//import org.apache.spark.ml.feature.LabeledPoint.getClass
object grammar {
  def main(args: Array[String]): Unit = {
    //    val myval :String = "zyc"
    //    println(myval)
    //    在 Scala 中，使用关键词 "var" 声明变量，使用关键词 "val" 声明常量
    //var VariableName : DataType [=  Initial Value]

    val pa = (40, "foo")
    //    pa:(Int,String= (40,Foo);
    val x = 100;
    if (x < 20) {
      println("x<20")
    } else if (x > 15) {
      if (x > 3) {
        println("kk")
      }
      while (true) {
        println("hh")
      }


    }
    else {
      println(s"2 = ${x}")
    }


    //    do {
    //      println('a')
    //    } while (a < 10)
    //    import scala.util.control._
    //    val loop = new Breaks;
    //    for (s <- 1 to 10) {
    //      loop.break();
    //    }


    def hh(a: Int, b: Int): Int = {
      var sum: Int = 0;
      sum = a + b
      return sum
    }

    //    var myvar: String = "sdasd"
    //    var multiplier = (i: Int) => i * 10
    //    val greeting : String = "hello!"
    //    val multiplier = (i:Int) => i*factor
    //    var factor  =3
    //    val multiplier  = (i:Int) => i*factor

  }

}
