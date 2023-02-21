package bigdata
object hdfsfiledemo {
  def main(args: Array[String]): Unit = {
    //引入SparkSession会话和隐式转换
//    import org.apache.spark.sql.SparkSession
//    //dataframe读取emp.json数据
//    val spark = SparkSession.builder().appName("hdfsfiledemo").master("local[*]").getOrCreate()
//    val df = spark.read.json("hdfs://emp.json")
//    df.show()
//
//
//    //dataframe转dataset
//    case class emp(empno: Int, ename: String, job: String, mgr: Int, hiredate: String, sal: Int, comm: Int, deptno: Int)
//    import spark.implicits._
//    val ds = df.as[emp]
//    ds.show()
//
//    //dataset查询数据
//    ds.filter($"sal" > 2000).show()
//    ds.filter($"sal" > 2000).select($"ename", $"sal").show()
//    ds.filter($"sal" > 2000).select($"ename", $"sal").orderBy($"sal".desc).show()
//    ds.filter($"sal" > 2000).select($"ename", $"sal").orderBy($"sal".desc).limit(3).show()
//    ds.filter($"sal" > 2000).select($"ename", $"sal").orderBy($"sal".desc).limit(3).write.json("hdfs://emp2.json")
//
//    //关闭会话
//    spark.stop()
//

  }
}
