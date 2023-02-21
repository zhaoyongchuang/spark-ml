package test

object copilot_test {
  def main(args: Array[String]): Unit = {
//    （1）在SogouAnalysis中定义main方法，在main方法中实现数据读取业务。
//    （2）创建SparkContext环境。
//    （3）读取日志数据，将数据收集到rawLogsRDD中。
//    （4）对rawLogsRDD数据进行解析，封装到SogouRecord中，并将数据收集到recordsRDD。
//    过滤null数据以及切割后长度不等于6的数据。
//    对每个分区中的数据进行解析，封装到SogouRecord。
//    （5）recordsRDD数据在后续经常调用，因此进行缓存。使用内存加缓存方式，通过count触发。
//    （6）获取搜索词，进行中文分词。
//    （7）对分词后的数据进行过滤，过滤掉长度小于2的数据。
//    （8）对分词后的数据进行映射，映射为(word,1)的形式。
//    （9）对映射后的数据进行聚合，聚合为(word,count)的形式。
//    （10）对聚合后的数据进行排序，排序后取前10个数据。
//    （11）将结果打印到控制台。
//    （12）关闭SparkContext环境。

//    val conf = new SparkConf().setAppName("SogouAnalysis").setMaster("local")
//    val sc = new SparkContext(conf)
//    val rawLogsRDD = sc.textFile("C:\\Users\\Administrator\\Desktop\\sogou\\sogou.100w.utf8")
//    val recordsRDD = rawLogsRDD.map(line => {
//      val fields = line.split("\t")
//      if (fields.length == 6) {
//        SogouRecord(fields(0), fields(1), fields(2), fields(3), fields(4), fields(5))
//      } else {
//        null
//      }
//    }).filter(_ != null).cache()
//    val searchWordsRDD = recordsRDD.map(record => record.query)
//    val wordsRDD = searchWordsRDD.flatMap(word => {
//      val seg = HanLP.segment(word)
//      val words = new ArrayBuffer[String]()
//      for (term <- seg) {
//        words += term.word
//      }
//      words
//    }).filter(_.length >= 2)
//    val wordAndOneRDD = wordsRDD.map(word => (word, 1))
//    val reducedRDD = wordAndOneRDD.reduceByKey(_ + _)
//    val sortedRDD = reducedRDD.sortBy(_._2, false)
//    val top10 = sortedRDD.take(10)
//    top10.foreach(println)
//    sc.stop()

  }

}
