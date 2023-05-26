package scala.canshu_hanshu

import scala.collection.mutable.ListBuffer

object new_hanshu {
  def main(args: Array[String]): Unit = {
//    使用参数函数，判断是否是水仙花数,水仙花数是指一个3位数，其各位数字立方和等于该数本身
    def isshuixianhua(n:Int):Boolean={
      var a = n%10
      var b = n/10%10
      var c = n/100;
      a*a*a+b*b*b+c*c*c==n
    }
    def func1(array: Array[Int],fp:Int=>Boolean)={  // fp:Int=>Boolean是参数函数,Int->Boolean是函数类型
//      var lst  = List[Int]()
      var lst = ListBuffer[Int]()
      for (i<-array){
        if (fp(i)){
          lst.append(i)
        }
      }
      lst
    }
//    func1(Array(153,370,371,407,1,2,3,4,5,6,7,8,9,10),isshuixianhua).foreach(println(_))
//    设置一个900个数组，从100，到999
    def isgreat900(n:Int):Boolean={
      n>900
    }



    var array = new Array[Int](900)
    for (i<-0 to 899){
      array(i) = i+100
    }
    func1(array,isshuixianhua).foreach(println(_))
//    func1(array,isgreat900).foreach(println(_))

  }
}
