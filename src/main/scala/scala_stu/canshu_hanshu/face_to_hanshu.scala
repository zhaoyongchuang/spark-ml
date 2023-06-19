package scala.canshu_hanshu

object face_to_hanshu {
  def main(args: Array[String]): Unit = {
//    面向函数编程
//    var fp1 = f1()
//    fp1
//    println(fp1)
//  匿名函数的使用方法
//    fp2是一个变量，变量的类型是函数
    var fp2=()=>{println("fp2 hello")}
    fp2()

    var fp3=(x:String)=>("fp3 hello"+x)
    println(fp3("zyc"))
//    高阶函数-把函数作为参数传递给另外一个函数，函数类型的形参
    def function1(string: String,m:(String=>Unit)): Unit ={
      m(string)
    }
    def function2(string: String,fp:(String=>String))={
      fp(string)
//      fp("test"+string)
    }
    def f1(string: String)={
      (string+"zyc-f1")
    }
    def f2(string: String)={
      (string+"zyc-f2")
    }
    println(function2("hello",f1))
    println(function2("hello2",f2))
//    实参
    println(function2("zyc",(string:String)=>{"hellooo"+string}))




    def function3(i:Int,j:Int,fp:((Int,Int)=>Int))={
      fp(i,j)
    }
    def jia(i:Int,j:Int)={
      i+j
    }
    def jian(i:Int,j:Int)={
      i-j
    }
    def chen(i:Int,j:Int)={
      i*j
    }
    println(function3(1,2,jia))
    println(function3(1,2,jian))
    println(function3(1,2,chen))
    def function4(array: Array[Int],fp:(Array[Int])=>Int)={
      fp(array)
    }
    def sum(array: Array[Int])={
      var sum = 0
      for (elem <- array) {
        sum += elem
      }
      sum
    }
    println(function4(Array(1,2,3,4,5),sum))

  }

//  函数参数数组
  private def function5(string: String, fps:Array[(String=>String)])={
    for (fp <- fps) {
      println(fp(string))
    }
  }
  def f11(string: String) = {
    (string + "zyc-f1")
  }

  def f21(string: String) = {
    (string + "zyc-f2")
  }
  function5("zyc123456===",Array(f11,f21))
  def function_array_array(array: Array[Int],fps:Array[(Array[Int])=>Int])={
    for(fp<-fps){
      println(fp(array))

    }
  }
  def sum1(array: Array[Int])={
    var sum = 0
    for (elem <- array) {
      sum += elem
    }
    sum
  }
  def sum2(array: Array[Int])={
    var sum = 0
    for (elem <- array) {
      sum += elem
    }
    sum
  }

  function_array_array(Array(1, 2, 3, 4, 5), Array(sum1, sum2))


//    函数做参数，处理奇偶数判断
  def fucnction_jiou(n:Int,fp:Int=>Boolean)={
    fp(n)
  }
  def fucnction_jiou2(array: Array[Int],fp:Int=>Boolean)={
    for (elem <- array) {
      println(fp(elem))
    }
  }
  def myf(n:Int)={
    (n%2==0)
  }
  println("=========================")
  println(fucnction_jiou(2,myf))
  println(fucnction_jiou2(Array(1, 2, 3, 4), myf))
  println("========================")






}

