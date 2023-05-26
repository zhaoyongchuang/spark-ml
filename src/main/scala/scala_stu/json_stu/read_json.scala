package scala.json_stu

import net.minidev.json.JSONObject
import net.minidev.json.parser.JSONParser


object read_json {
  def main(args: Array[String]): Unit = {
//    val conf = new org.apache.spark.SparkConf()
//      .setAppName("read_json")
//      .setMaster("local[*]")
//    val sc = new org.apache.spark.SparkContext(conf)

    val rdd1 = "{\"name\":\"jerry\",\"age\":25,\"phone\":\"18810919225\"}"
    val jsonParser = new JSONParser()
    val jsonObj: JSONObject = jsonParser.parse(rdd1).asInstanceOf[JSONObject]
    val name = jsonObj.get("name").toString
    println(name)
    val jsonKey = jsonObj.keySet()
    val iter = jsonKey.iterator
    while (iter.hasNext) {
      val instance = iter.next()
      val value = jsonObj.get(instance).toString
      println(instance + " --->" + value)
    }








  }
}
