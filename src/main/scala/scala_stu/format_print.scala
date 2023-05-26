package scala

object format_print {
  def main(args: Array[String]): Unit = {
    def print(id: Int, name: String, age: Int): Unit = {
      println("id:" + id + " name:" + name + " age:" + age)
    }
    print(1, "zhangsan", 20)
  }
  def print1(id: Int=2, name: String="name", age: Int=2): Unit = {
    println("id:" + id + " name:" + name + " age:" + age)
  }
  print1(1,"zhangsan", 20)

}
