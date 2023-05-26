package study

import org.apache.spark.ml.feature.StandardScaler
import org.apache.spark.sql.SparkSession

object standard {
  def main(args: Array[String]): Unit = {
    //z 分数法
    val spark: SparkSession = SparkSession
      .builder()
      .appName("spark_scaler")
      .master("local[*]")
      .getOrCreate()
    val dataframe = spark.read.format("libsvm").load("data/mllib/sample_libsvm_data.txt")
    val scaler = new StandardScaler()
      .setInputCol("features")
      .setOutputCol("scaledFeatures")
      .setWithStd(true)
      .setWithMean(false)
    // 计算汇总统计量，生成scalermodel
    val scalerModel = scaler.fit(dataset = dataframe)
    // 对特征进行标准化
    val scaledData = scalerModel.transform(dataframe)
    scaledData.show()


  }

}
