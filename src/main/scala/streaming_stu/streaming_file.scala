package streaming_stu

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object streaming_file {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf()
      .setAppName("wc streaming")
      .setMaster("local[2]")
    val stream1 = new StreamingContext(conf, Seconds(5))
    val rdd1 = stream1.textFileStream("D:/logs/")
    rdd1.flatMap(_.split(" "))
      .map((_,1))
      .reduceByKey(_+_)
      .print()
    stream1.start()
    stream1.awaitTermination()


  }
}
