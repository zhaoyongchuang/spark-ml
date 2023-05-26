package scala

object hanshu {
  def main(args: Array[String]): Unit = {
//    def
    def f4(a:Int,b:Int)=a+b
    def f(p1:Int,p2:Int):Int={ //有返回值的函数带有等号，没有返回值的函数不带等号
      p1+p2//类型自动推断
    }
    def hello():Unit={
      println("hello")
    }
    println(f(1,2))
    hello()
    hello
    println(f4(1,2))

  }
}
