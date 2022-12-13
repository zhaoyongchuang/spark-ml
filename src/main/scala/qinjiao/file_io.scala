package qinjiao

import au.com.bytecode.opencsv.CSVReader
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.json4s.ShortTypeHints
import org.json4s.jackson.JsonMethods._
import org.json4s.jackson.Serialization

import java.io.StringReader
import scala.util.parsing.json.JSON
object file_io {
  def main(args: Array[String]): Unit = {

    val sc :SparkContext = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("file_io"))
    // 读取HDFS文件
    val file = sc.textFile("hdfs://192.168.222.181:9000")
    // 写入HDFS文件系统
    file.saveAsTextFile("hdfs://主机名:端口号+文件路径")

    // ======json
    val lines = sc.textFile("file://")
    val res: RDD[Option[Any]] = lines.map(s => JSON.parseFull(s))
    res.foreach({
      // 使用偏函数获取json数据结构
      r =>r match {
          // 打印数据
          case Some(map: Map[String, Any]) => println(map)
          case None => println("nothing")
          case _ => println("unknow data structure")
        }
    })

    implicit val formats = Serialization.formats(ShortTypeHints(List()))



    val input = sc.textFile("")
    val result = input.map{
      line => val reader = new CSVReader(new StringReader(line))
      reader.readNext()
    }

    for (res <- result){
      for (r<- res){
        println(r)
      }
    }




  }
}
