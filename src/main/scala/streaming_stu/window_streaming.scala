package streaming_stu

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object window_streaming {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("streaming window")
      .setMaster("local[2]")
    val stream1 = new StreamingContext(conf, Seconds  (10))
    val rdd1 = stream1.socketTextStream("192.168.222.184", 9999)
    val rdd2 = rdd1.window(Seconds(10),Seconds(10))
    rdd2.flatMap(_.split(" "))
      .map((_, 1))
      .reduceByKey(_ + _).print()
    stream1.start()
    stream1.awaitTermination() //等待结束

  }
}
