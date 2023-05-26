package scala.use_gaojie

object map_filter {
  def main(args: Array[String]): Unit = {
//    map
    println(Map("pen"->9,"bread"->12,"book"->29).filter(_._1.contains("pen")))
    println(Map("pen"->9,"bread"->12,"book"->29).filter(_._2>12))
//    元组
    println(Array(("一号窗口","bread",10),("second","rice",1)).filter(_._3>2).toList)

//    println(_)不能使用输出拼接。




  }
}
