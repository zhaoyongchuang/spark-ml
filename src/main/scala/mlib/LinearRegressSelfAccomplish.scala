package mlib

import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.rdd.RDD
import org.jblas.DoubleMatrix

object LinearRegressSelfAccomplish {
  def main(args: Array[String]): Unit = {
    val conf = new org.apache.spark.SparkConf().setAppName("wordcount").setMaster("local[*]")
    val sc = new org.apache.spark.SparkContext(conf)


    val path = "D:\\spark-master\\data\\mllib\\sample_linear_regression_data.txt"
    val Linear = new LinearRegressSelf()
    val data: RDD[LabeledPoint] = Linear.loaddata(path)
    //data.foreach(println)

    //处理数据
    val xydata: RDD[(Double, Array[Double])] = Linear.process(data)
    //标签数据
    val label: Array[Double] = xydata.map(x => x._1).collect()
    //特征数据
    val features: Array[Array[Double]] = xydata.map((x => x._2)).collect()

    //设置参数
    val alpha = 0.01

    //xydata.foreach(println)
    val matrix: DoubleMatrix = Linear.LiearRegressScala(data, xydata, alpha = alpha)
    //println(matrix)
    //保存模型
    val save: Unit = Linear.saveWeights(matrix, "D:\\spark-master\\output\\Linearweights.txt")


  }
}
