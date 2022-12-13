package qinjiao

import com.hankcs.hanlp.HanLP
import com.hankcs.hanlp.seg.common.Term
import com.hankcs.hanlp.tokenizer.StandardTokenizer

import java.util
import scala.collection.JavaConverters._
/**
 * HanLP 入门案例，基本使用
 */
object HanLPTest {
  def main(args: Array[String]): Unit = {
    // 入门Demo

    val terms: util.List[Term] = HanLP.segment("Spark Sogou日志分析")
    println(terms)
    println(terms.asScala.map(_.word.trim))
// 访问时间\t用户ID\t[查询词]\t该URL在返回结果中的排名\t用户点击的顺序号\t用户点击的URL
    // 标准分词
    val terms1: util.List[Term] = StandardTokenizer.segment("spark++scala++HanLP")
    println(terms1)
    println(terms1.asScala.map(_.word.replaceAll("\\s+", "")))

    val words: Array[String] =
      """00:00:00 2982199073774412    [360安全卫士]   8 3 download.it.com.cn/softweb/software/firewall/antivirus/20067/17938.html"""
        .split("\\s+")
    println(words(2).replaceAll("\\[|\\]", ""))//将"["和"]"替换为空""
  }

}
