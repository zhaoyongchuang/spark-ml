package scala

object reudce_stu {
  def main(args: Array[String]): Unit = {
//    reduce function study
    println(List(1,2,3,4,5).reduce(_+_)) // reduce 函数 等于(a:Int,b:Int)=>a+b

    def sum_list(a:Int,b:Int):Int={
      a+b
    }
    println(List(1,2,3,4,5).reduce(sum_list))
//    reduce ,reduceLeft ,reduceRight不同之处在于
//    reduceLeft 从左边开始计算
//    reduceRight 从右边开始计算
    println(List(1,2,3,4,5).reduceLeft(_-_)) // -13
    println(List(1,2,3,4,5).reduceRight(_-_))  // 3




  }
}
