package qinjiao

import org.apache.spark.{SparkConf, SparkContext}

object word2vec {
  def   main(args: Array[String]): Unit = {
    import org.apache.spark.mllib.feature.{HashingTF, IDF}
    import org.apache.spark.mllib.linalg.Vector
    import org.apache.spark.rdd.RDD

    //加载文件（每行一个）
    val conf = new SparkConf().setMaster("local")
      .setAppName("word2vec")
    val sc = new SparkContext(conf)
    val documents: RDD[Seq[String]] = sc.textFile("/root/example-files/sequencefile.txt").map(_.split(",").toSeq)
    val hashingTF = new HashingTF()
    val tf: RDD[Vector] = hashingTF.transform(documents)
    // 在应用 HashingTF 时，只需要单次传递数据，应用 IDF 需要两次：
    // 首先计算 IDF 向量，其次用 IDF 来缩放字词频率。
    tf.cache()
    val idf = new IDF().fit(tf)
    val tfidf: RDD[Vector] = idf.transform(tf)
    // spark.mllib IDF 实现提供了忽略少于最少文档数量的字词的选项。
    //在这种情况下，这些条款的 IDF 设置为0。
    // 通过将minDocFreq值传递给IDF构造函数，可以使用此功能。
    val idfIgnore = new IDF(minDocFreq = 2).fit(tf)
    val tfidfIgnore: RDD[Vector] = idfIgnore.transform(tf)
    tfidf.foreach(x => println(x))
    tfidfIgnore.foreach(x => println(x))
  }
}
