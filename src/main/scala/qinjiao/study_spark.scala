package qinjiao

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object study_spark {
  def main(args: Array[String]): Unit = {
    // 函数与方法的区别
    // 定义一个方法实现两个整数相加
    def add1(a:Int, b:Int) = a + b
    //调用方法求1+2和
    println(add1(1, 2))

    // 定义一个计算两个值相加的方法，这两个值默认为0
    // 调用该方法，不传任何参数
    // x，y带有默认值为0
    def add2(x:Int = 0, y:Int = 0) = x + y;
    println(add2(1, 2))
    println(Math.abs(-1))

    // 定义一个两个数值相加的函数
    val add3 = (x: Int, y: Int) => x + y

    // 调用该函数
    println(add3(1, 2))

    // 定义一个方法用来进行两个数相加
    def add4(x: Int, y: Int) = x + y

    // 将该方法转换为一个函数，赋值给变量
//    val a = add4 _
//
//    println(a(1, 6))
//
//    val add5 = (x:Int,y:Int) =>x+y
//    println(add5(2, 3))

    // 集合的操作，
//    val arr1 = new Array[Int](3)
//    val arr1  = Array(1,2,3,9)
//    for( i<- arr1){
//      println(i)
//    }
    // 创建空的ArrayBuffer变长数组


    // 创建带有初始元素的ArrayBuffer
//    var arrayBuffer = ArrayBuffer[Int](11,12,13,14)
//    for (x<-arrayBuffer){
//      println(x)
//    }
//    arrayBuffer++=arr1
//    println(arrayBuffer.sum)
//    println(arrayBuffer.max)
//    val map = Map(1->2, 2->3, 4->5)
//    println(map.keys)
//    println(map.getOrElse(1,"0"))
//    map.values

//    println(map(1))


    // 定义元组
//    val t = ("hadoop", 11)
//    // 获取第一个元素
//    println(t._1)
//    val arr_case  = Array("x","y","z")
//    val a_case =arr_case(Random.nextInt(arr_case.length))
//    a_case match{
//      case "x" =>println("")
//    }

    // 文件读写
    // 导入依赖包
    import scala.io.Source
    // 读取文件信息
//    val file = Source.fromFile("src/anhui/user_comments.txt")

    //获取每行数据
//    val lines = file.getLines()
//    println(lines)

    // 导入依赖包
    import scala.io.Source
    // 读取文件信息
//    val 变量名 = Source.fromFile()
    //获取字符
//    for (char <- file) {
//      println(char)
//    }
//    val source = Source.fromURL("http://www.baidu.com")
//    val lines = source.getLines()
//    for (i <- lines) {
//      println(i)
//    }


//    val source = Source.fromFile("/root/software/hadoop-2.7.7/README.txt")
//    val contents = source.mkString.split(" ")
//    for (word <- contents) {
//      println(word)
//    }


//    def main(args: Array[String]) {
//      val writer = new PrintWriter(new File("test.txt"))
//      writer.write("欢迎来到青椒课堂")
//      writer.close()
//    }


    val arr = Array(1, 2, 3)
    //匿名函数(x:Int) => x*2被传入map
    arr.map((x: Int) => x * 2)
    //相对简洁的写法，把参数类型拿掉
    arr.map(x => x * 2)
    //更为简洁的写法，使用下划线代表数组中元素
    arr.map(_ * 2)
//  map((x:Int) => x*2)
//    arr.map(x => x*2)
    for(x <- arr.map(_ *2)){
      println(x)
    }


    //普通定义:def curry(x:Int,y:Int) = x+y
    //柯里化
    def curry(x: Int)(y: Int) = x + y

    curry(2)(4)
    //方法显示转换成函数
    val curry1 = curry(2) _
    curry1(4)

    // spark shell
//    val conf =
//    val sc = new SparkContext()
//    val textFile = sc.textFile("hdfs://localhost:9000/wordcount/input/word.txt")
//    val counts = textFile.flatMap(_.split(" ")).map((_, 1)).reduceByKey(_ + _)
//    counts.collect
//    counts.saveAsTextFile("hdfs://localhost:9000/wordcount/output")
//      ========RDD的创建


    val linesSeq:Seq[String] = Seq(
      "hello",
      "hello world",
      "hello hadoop",
      "hello zyc"
    )
    // 并行化的方式将数据收集到RDD集合
    val conf: SparkConf = new SparkConf().setMaster("RDD")
    val sc:SparkContext = new SparkContext(conf)
    val inputRDD:RDD[String]  = sc.parallelize(linesSeq,numSlices = 2)


    // 读取文件
    val inputRDD_filelocal:RDD[String] = sc.textFile("D:/opt/")


  }
}
