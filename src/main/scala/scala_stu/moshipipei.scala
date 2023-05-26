package scala

object moshipipei {
// 模式匹配
def main(args: Array[String]): Unit = {
  mymatch(5)
  mymatch(Map("k"->12))





  def mymatch(x:Any):Any = x match {
    case y:Int=>println("INt")
    case string: String=>println("string")
    case map: Map[String,Int]=>println("map")
    case _=>println("else")
  }
}
}
