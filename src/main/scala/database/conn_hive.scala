package database

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.jdbc.{JdbcDialect, JdbcDialects}

object conn_hive {
  def main(args: Array[String]): Unit = {
//    连接hive数据库，192.168.222.182:10000/zyc1113
  val spark = new SparkSession.Builder()
    .appName("connect hive")
    .master("local[*]")
    .getOrCreate()
    register()
    import spark.implicits._
//    连接hive数据库，192.168.222.182:10000/zyc1113

    val df1 = spark.read
      .format("jdbc")
      .option("url","jdbc:hive2://192.168.222.182:10000")
      .option("driver","org.apache.hive.jdbc.HiveDriver")
      .option("dbtable","stu_0")
      .option("user","root")
      .option("password","123456")
      .load()
    df1.show()
  }

  def register(): Unit = {
    JdbcDialects.registerDialect(HiveSqlDialect)
  }


  case object HiveSqlDialect extends JdbcDialect {
    override def canHandle(url: String): Boolean = url.startsWith("jdbc:hive2")

    override def quoteIdentifier(colName: String): String = {
      colName.split('.').map(part => s"`$part`").mkString(".")
    }
  }
}
