package film_recommend

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.evaluation.RankingMetrics
import org.apache.spark.mllib.recommendation.{ALS, Rating}
import org.apache.spark.rdd.RDD
import org.jblas.DoubleMatrix
//import sql.StreamingExamples

object MAPKTest {
    def main(args: Array[String]) {
      //    StreamingExamples.setStreamingLogLevels()
      val conf = new SparkConf().setAppName("MAPKTest").setMaster("local[*]")
      val sc = new SparkContext(conf)
      /*?? ?? ??*/
      val rawData: RDD[String] = sc.textFile("file:/headless/Downloads/ScalaProject/ml-100k/u.data")
      //????????rawRatings:Array
      val rawRatings = rawData.map(_.split("\\t").take(3))
      //user moive rating
      val ratings = rawRatings.map { case Array(user, movie, rating) => {
        Rating(user.toInt, movie.toInt, rating.toDouble)
      }
      }
      /**
       * ???????
       * ???50????????????????????? ????
       */
      val model = ALS.train(ratings, 50, 10, 0.01)

      /*?????????? factor???????*/
      val itemFactors: Array[Array[Double]] = model.productFeatures.map { case (id, factor) => factor }.collect()
      val itemMatrix: DoubleMatrix = new DoubleMatrix(itemFactors)
      //    println(itemMatrix.rows, itemMatrix.columns)

      /*???????????????????*/
      val allRecs = model.userFeatures.map { case (userId, factor) => {
        val userVector = new DoubleMatrix(factor)
        /**
         * socres???DoubleMatrix?????1?N?? Vector
         * ??????????????????????????????
         * ????ALS?????????? ??-?? ??? ??? ?????????
         * ??????????????? ??
         */
        val scores = itemMatrix.mmul(userVector) //??????????????????
        //????????
        val sortedWithId = scores.data.zipWithIndex.sortBy(-_._1)
        //(score, itemId)
        val recommendIds = sortedWithId.map(_._2 + 1).toSeq
        //???? ? ??????????? ? tuple: (userId,(sorce, itemId))
        (userId, recommendIds)
      }
      }

      /*?????? ?????????????????*/
      val userMoives: RDD[(Int, Iterable[(Int, Int)])] = ratings.map { case Rating(user, product, rating) => {
        (user, product)
      }
      }.groupBy(_._1)

      val predictedAndTrueForRanking = allRecs.join(userMoives).map { case (userId, (predicted, actualWithIds)) => {
        //???????
        val actual = actualWithIds.map(_._2)
        (actual.toArray, predicted.toArray)
      }
      }
      val rankingMetrics: RankingMetrics[Int] = new RankingMetrics(predictedAndTrueForRanking)
      println("???????MAP?" + rankingMetrics.meanAveragePrecision)
    }
}
