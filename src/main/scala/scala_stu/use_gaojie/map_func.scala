package scala.use_gaojie

object map_func {
  def main(args: Array[String]): Unit = {
//    println(Map("k1" -> 19, "k2" -> 20, "k3" -> 21, "k4" -> 20).map(_._1).toList)
//    List("c","java","c++","c","c++") 进行map reduce 统计
//    println(List())
//    println(List("c","java","c++","c","c++").map((_,1)).reduce((_._1)))
    def myf1(string: String,i:Int):Map[String,Int]={
      Map(string->i)
    }
//    val tb1 = ("1","12","3")
//    println(tb1._1)
//    将俩个列表合并成一个map
    val list1 = List("1","2","3")
    val list2 = List("a","b","c")
    for(i <- list1.indices){
      Map(list1(i)->list2(i)).foreach(println(_))



    }








  }
}
