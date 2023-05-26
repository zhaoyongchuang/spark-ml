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