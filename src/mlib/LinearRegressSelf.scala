package mlib


import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.jblas.DoubleMatrix

import java.io.{File, PrintWriter}
import scala.io.Source

class LinearRegressSelf {
  def loaddata(path: String) = {
    val conf: SparkConf = new SparkConf().setMaster("local").setAppName("simpletest")
    val sc = new SparkContext(conf)

    val sourcedata: RDD[LabeledPoint] = MLUtils.loadLibSVMFile(sc, path)
    sourcedata

  }

  //-------------------------------------数据预处理---------------------------------------------------
  def process(sourcedata: RDD[LabeledPoint]) = {
    val data: RDD[(Double, Array[Double])] = sourcedata.map(
      x => (x.label, 1.0 +: x.features.toArray)
    )
    data

  }

  //-------------------------------------进行训练处理---------------------------------------------------
  def LiearRegressScala(sourcedata: RDD[LabeledPoint], data: RDD[(Double, Array[Double])],
                        alpha: Double = 0.01) = {
    //初始化权重向量
    //特征数
    val numFeatures: Int = sourcedata.first().features.toArray.length
    val initialweights = new Array[Double](numFeatures)

    val initialweightwithIntercept = 0.0 +: initialweights //在数组首部添加元素0

    val numExamples: Int = data.count().toInt
    //DoubleMatrix(int newRows, int newColumns, double... newData)
    var weights = new DoubleMatrix(initialweightwithIntercept.length, 1, initialweightwithIntercept: _*)
    /*println("initial weights: " + weights)
    weights*/

    val label: Array[Double] = data.map(x => x._1).collect()
    val features: Array[Array[Double]] = data.map((x => x._2)).collect()

    var hypothesis = 0.0
    var midError = 0.0
    var loss: Double = 10.0

    for (k <- 0 until numExamples
         if (loss > 0)
         ) {
      val i: Int = (new util.Random).nextInt(numExamples)
      val variable = new DoubleMatrix(features(i).length, 1, features(i): _*)
      hypothesis = variable.dot(weights) //计算y值
      midError = label(i) - hypothesis //误差

      weights = weights.add(variable.mul(alpha * midError))
      //val matrix: DoubleMatrix = weights.add(variable.mul(alpha * midError))
      println("the current weights: " + weights)
      //保存


      var cacheLoss = 0.0
      for (j <- 0 to (numExamples - 1)) {
        val multiplier = new DoubleMatrix(features(j).length, 1, features(j): _*)
        cacheLoss += (label(j) - weights.dot(multiplier)) * (label(j) - weights.dot(multiplier))
      }
      loss = 0.5 * cacheLoss / numExamples
      println("the current loos :" + loss)

    }
    weights

  }

  //-------------------------------------保存权值---------------------------------------------------
  def saveWeights(matrix: DoubleMatrix, savepath: String) = {
    val array: Array[Double] = matrix.toArray

    val write = new PrintWriter(new File(savepath))
    write.println(array.mkString(" "))
    write.close()
  }

  //-------------------------------------加载权重---------------------------------------------------
  def loadWeight(path: String) = {
    val file = Source.fromFile(path)
    val array: Array[Array[Double]] = file.getLines().map(_.split(" ")).map(_.map(_.toDouble)).toArray
    file.close()

    val flatten: Array[Double] = array.flatten
    //
    val matrix = new DoubleMatrix(flatten.length, 1, flatten: _*)
    matrix
  }

  ////-------------------------------------计算误差---------------------------------------------------
  def getError(data: RDD[(Double, Array[Double])], matrix: DoubleMatrix) = {

    val label: Array[Double] = data.map(x => x._1).collect()
    //val d1: Double = label(0)
    val features: Array[Array[Double]] = data.map((x => x._2)).collect()


    val numExamples: Int = data.count().toInt
    var sum: Double = 0.0

    for (k <- 0 until numExamples) {
      val i: Int = (new util.Random).nextInt(numExamples)
      val variable = new DoubleMatrix(features(i).length, 1, features(i): _*)
      val d: Double = variable.dot(matrix)
      //计算y值
      val midError = math.pow((label(i) - d), 2) //平方
      sum += midError
    }
    math.sqrt(sum / numExamples)

  }

}

