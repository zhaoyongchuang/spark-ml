package scala_stu

object bibao2 {
  def main(args: Array[String]): Unit = {
    val fp1 = function1(5.6)
    println(fp1(3))
//    柯里化
    println(function1(5.6)(3))
    }
//  reduceRight

  def function1(a: Double) = (b: Int) => {
    a + b
  }


}
