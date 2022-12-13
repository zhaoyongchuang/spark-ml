package com.hongya.sogouanalysis

import com.hankcs.hanlp.HanLP
import com.hankcs.hanlp.seg.common.Term
import org.apache.spark.rdd.RDD
import org.apache.spark.storage.StorageLevel
import org.apache.spark.{SparkConf, SparkContext}

import java.util
object SogouAnalysis {

  // 定义main方法，实现数据读取


  def main(args: Array[String]): Unit = {
    // 创建SparkContext对象
    val sparkConf: SparkConf = new SparkConf()
      .setAppName(this.getClass.getSimpleName.stripSuffix("$"))
      .setMaster("local[*]")
    val sc: SparkContext = new SparkContext(sparkConf)
    sc.setLogLevel("WARN")

    // TODO: 1. 本地读取SogouQ用户查询日志数据
    val rawLogsRDD: RDD[String] = sc.textFile("src/main/scala/com/hongya/sogouanalysis/sougou.csv")
    //println(s"Count = ${rawLogsRDD.count()}")

    // TODO: 2. 解析数据，封装到CaseClass样例类中
    val recordsRDD: RDD[SogouRecord] = rawLogsRDD
      // 过滤不合法数据，如null，分割后长度不等于6
      .filter(log => log != null && log.trim.split("\\s+").length == 6)
      // 对每个分区中数据进行解析，封装到SogouRecord
      .mapPartitions(iter => {
        iter.map(log => {
          val arr: Array[String] = log.trim.split("\\s+")
          SogouRecord(
            arr(0),
            arr(1),
            arr(2).replaceAll("\\[|\\]", ""),//通过正则匹配将“[]"替换成空字符串（HanLp分词无法过滤字符）
            arr(3).toInt,
            arr(4).toInt,
            arr(5)
          )
        })
      })

    // println(s"Count = ${recordsRDD.count()},\nFirst = ${recordsRDD.first()}")

    // 数据使用多次，进行缓存操作，使用count触发
    recordsRDD.persist(StorageLevel.MEMORY_AND_DISK).count()
    // =================== 搜索关键词统计 ===================
    // 获取搜索词，进行中文分词
    val wordsRDD: RDD[String] = recordsRDD.mapPartitions(iter => {
      iter.flatMap(record => {
        // 使用HanLP中文分词库进行分词
        val terms: util.List[Term] = HanLP.segment(record.queryWords.trim)
        // 将Java中集合对转换为Scala中集合对象
        import scala.collection.JavaConverters._
        terms.asScala.map(_.word)
      })
    })
    // println(s"Count = ${wordsRDD.count()}, Example = ${wordsRDD.take(5).mkString(",")}")

    // 统计搜索词出现次数
    val result1: RDD[(Int, String)] = wordsRDD
      .map((_, 1))// 每个单词出现一次
      .reduceByKey(_ + _)// 分组统计次数
      .map(_.swap)
      .sortByKey(false)// 词频降序排序
    result1
      .coalesce(1)//使用一个分区
      .saveAsTextFile("src/main/scala/com/hongya/sogouanalysis/keywords_opt" )//将数据写入指定路径
    // b. 获取次数最多Top10打印
    result1.take(10).foreach(println)
    // ===================  用户搜索点击次数统计 ===================
    /*
        每个用户在搜索引擎输入关键词以后，统计点击网页数目，反应搜索引擎准确度
        先按照用户ID分组，再按照搜索词分组，统计出每个用户每个搜索词点击网页个数
     */
    val clickCountRDD: RDD[((String, String), Int)] = recordsRDD
      .map(record => {
        // 获取用户ID和搜索词
        val key = (record.userId, record.queryWords)
        (key, 1)
      })
      // 按照用户ID和搜索词组合的Key分组聚合
      .reduceByKey(_ + _)
      .sortBy(_._2, ascending = false)
    clickCountRDD.repartition(1).saveAsTextFile("src/main/scala/com/hongya/sogouanalysis/click_opt")
    // 控制台打印前十行
    clickCountRDD.take(10).foreach(println)
    // 控制台打印最大、最小、平均值
    println(s"Max Click Count = ${clickCountRDD.map(_._2).max()}")
    println(s"Min Click Count = ${clickCountRDD.map(_._2).min()}")
    println(s"Avg Click Count = ${clickCountRDD.map(_._2).mean()}")
    // =================== 搜索时间段统计 ===================
    /*
        从搜索时间字段获取小时，统计个小时搜索次数
     */
    val hourSearchRDD: RDD[(String, Int)] = recordsRDD
      // 提取小时和分钟
      .map(record => {
        // 03:12:50
        record.queryTime.substring(0, 5)
      })
      // 分组聚合
      .map((_, 1)) // 每个单词出现一次
      .reduceByKey(_ + _) // 分组统计次数
      .sortBy(_._2, ascending = false)
    // 数据写入文件中
    hourSearchRDD.coalesce(1).saveAsTextFile("src/main/scala/com/hongya/sogouanalysis/hour_opt")
    // 控制台打印前十行
    hourSearchRDD.take(10).foreach(println)
    // 释放缓存数据
    recordsRDD.unpersist()

    // 应用结束，关闭资源
    sc.stop()
  }

  /**

   * 用户搜索点击网页记录Record
   * queryTime  访问时间，格式为：HH:mm:ss
   * userId     用户ID
   * queryWords 查询词
   * resultRank 该URL在返回结果中的排名
   * clickRank  用户点击的顺序号
   * clickUrl   用户点击的URL

   */

  case class SogouRecord(
                          queryTime: String,
                          userId: String,
                          queryWords: String,
                          resultRank: Int,
                          clickRank: Int,
                          clickUrl: String
                        )

}
