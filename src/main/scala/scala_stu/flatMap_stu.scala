package scala


object flatMap_stu {
  def main(args: Array[String]): Unit = {
//    flatmap 函数学习
    println(List(List(1, 2, 3), List(4, 5), List(6, 7, 8)).flatten)
    println(List("hello world").flatMap(x=>x))
    println(List("hello world").flatMap(x=>x.split(" ")))
    println(List("hello world","hello scala").flatMap(x=>x.split(" ")))
    println(List("hello world","hello scala").flatMap(x=>x.split(" ")).map(x=>(x,1))) //map函数
  }
}
