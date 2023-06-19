package scala

import scala.beans.BeanProperty

object zhujie {
  def main(args: Array[String]): Unit = {
    val zs = new stu()
    zs.setAge("12")
    zs.setName("34")
    println(zs.getAge)
  }
}
protected class stu{
  @BeanProperty var name :String=_
  @BeanProperty var age :String=_
  def student()=println("hh")

}
// 注解的使用以及功能
// 1.注解的语法：@注解名(参数名=参数值)
// 2.注解的分类：
// 1.编译时注解：注解只在源码中存在，编译成字节码后注解就不存在了
// 2.类注解：注解可以用在类上
// 3.方法注解：注解可以用在方法上
// 4.字段注解：注解可以用在字段上
