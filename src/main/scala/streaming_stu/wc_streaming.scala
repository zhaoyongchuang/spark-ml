package streaming_stu

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object wc_streaming {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("wc streaming")
      .setMaster("local[2]")
    val stream1 = new StreamingContext(conf,Seconds(5))
    val rdd1 = stream1.socketTextStream("192.168.222.184",9999)
    val rdd2 = rdd1.flatMap(_.split(" "))
      .map((_,1))
      .reduceByKey(_+_)
    rdd2.print()
    rdd2.saveAsTextFiles("src/main/scala/streaming_stu/wc_data")
    stream1.start()
    stream1.awaitTermination()  //等待结束
  }
}
