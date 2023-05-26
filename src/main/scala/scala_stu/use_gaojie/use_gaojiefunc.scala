package scala.use_gaojie

object use_gaojiefunc {
  def main(args: Array[String]): Unit = {

//    Array(1,2,3,4,5,6).filter(_>3).foreach(println(_))
//    Array(1,2,3,4,5,6).filter(_%2==0).foreach(println(_))
//    _ 表示数组的每个元素
//    是否是质数的函数iszhishu
    def iszhishu(n:Int):Boolean={
      if (n<=1) false
      else if (n==2) true
      else !(2 until n).exists(x=>n%x==0)
    }
    Array(1,2,3,4,5,6,7,8,9,10).filter(iszhishu).foreach(println(_))


//    val stringArrays = Array("tom","jerry")
//   字符串操作
    println(Array("tom", "jerry", "tiom1").filter(_.startsWith("t")).toList)

//   正则表达式
    println(Array("tom", "jerry", "tiom1").filter(_.matches(".*m.*")).toList)
    println(Array("tom","team","top","toppp").filter(_.matches("t[a-z]+m")).toList)
    def mystring_filter(string: String):Boolean={
      string.length==3 && string.startsWith("t")
    }
    println(Array("tom","team","top").filter(mystring_filter).toList)




  }

}
