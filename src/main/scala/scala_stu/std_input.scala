package scala

import scala.io.StdIn
import scala.util.control.Breaks
import scala.util.control.Breaks._

object std_input {
  def main(args: Array[String]): Unit = {
    val name = "zyc"
    val password = "123456"
    val loop = new Breaks;
//    loop.breakable {
//      for (i <- 1 to 3) {
//        val name_input = StdIn.readLine()
//        val password_input = StdIn.readLine()
//        if (name_input == name && password_input == password) {
//          println("登录成功")
//          //        结束循环
////          break()
//          loop.break
//        }
//        else {
//          println("登录失败,账号或密码错误,还剩" + (3 - i) + "次")
//        }
//      }
//    }
    var i = 3
    while (i>0) {
      val name_input = StdIn.readLine()
      val password_input = StdIn.readLine()
      if (name_input == name && password_input == password) {
        println("登录成功")
        i = 0
      }
      else {
        println("登录失败,账号或密码错误,还剩" + (3 - i) + "次")
        i -= 1
      }
    }

    

  }
}
