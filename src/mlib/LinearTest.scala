package mlib

import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.rdd.RDD
import org.jblas.DoubleMatrix

object LinearTest {
  def main(args: Array[String]): Unit = {
    //-------------------------------------加载数据---------------------------------------------------
    //测试数据
    val Linear = new LinearRegressSelf()

    //加载模型
    println("加载模型：")
    val loadmatrix: DoubleMatrix = Linear.loadWeight("D:\\spark-master\\output\\Linearweights.txt")
    val pathdataTest = "D:\\spark-master\\data\\mllib\\sample_linear_regression_data_test.txt"
    val dataTest: RDD[LabeledPoint] = Linear.loaddata(pathdataTest)
    val TestRDD: RDD[(Double, Array[Double])] = Linear.process(dataTest)
    println("计算均方根误差")
    val d: Double = Linear.getError(TestRDD, loadmatrix)
    println("均方根误差为u：" + d)
  }
}
