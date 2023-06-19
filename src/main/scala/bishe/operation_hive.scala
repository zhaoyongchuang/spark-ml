package bishe

import org.apache.spark.sql.SparkSession

object operation_hive {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("BasicAnalyse")
      .master("local[*]")
      .enableHiveSupport()
      .getOrCreate()
    //    读取hive表数据
    val data = spark.sql("select * from tv_log_ods.tv_log_ods_table limit 10")
    data.show(10)
    spark.stop()
  }
}
