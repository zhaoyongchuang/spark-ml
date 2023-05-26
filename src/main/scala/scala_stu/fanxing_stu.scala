package scala
// 泛型的协变和逆变
// 协变：如果A是B的子类，那么List[A]是List[B]的子类
// 逆变：如果A是B的子类，那么List[B]是List[A]的子类
object fanxing_stu {
  def main(args: Array[String]): Unit = {
//    var p:Person = new Student()
    var m:Mylist[Person1] = new Mylist[Student] // 协变

//    var m1:Mylist[Student] = new Mylist[Person1] // 逆变




  }

}
class Person1{}

class Student extends Person1{}
class Mylist[+T]{} // +T表示协变
//class Mylist[-T]{} // -T表示逆变
