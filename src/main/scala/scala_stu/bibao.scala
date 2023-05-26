package scala

import scala.beans.BeanProperty
// 闭包：把一个人匿名函数复制给一个变量，这个变量就叫做闭包
// 闭包的作用：可以把一个函数当做一个值来使用
// 闭包的特点：可以访问外部的变量
// 闭包的缺点：会导致程序的内存泄漏
object bibao {
  def main(args: Array[String]): Unit = {
    val x = 10
    def f(y: Int): Int = {
      x + y
    }
    println(f(1))
  }
}
abstract class Person{
  @BeanProperty var name:String=_
  @BeanProperty var age:String=_
  def work()
  def printInfo()={
    println(name+":"+age)
  }
}
class Teacher extends Person{
  override def work()  = println("教学")
  override def printInfo()={
    println("teacher"+name+age)
  }
}
