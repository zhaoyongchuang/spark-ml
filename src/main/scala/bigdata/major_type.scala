package bigdata

import org.apache.spark.{SparkConf, SparkContext}

object major_type {
  def main(args: Array[String]): Unit = {
    //    professional.csv 是专业信息（professional），字段为学校,专业类别,专业名称,国家特色专业
    //    school.csv 是学校信息（school），字段为学校,省份,城市,地址,水平层次,办学类别,办学类型,985,211,双一流,归属,开设专业链接

//    Hadoop配置文件是以内网IP作为机器间通信的IP。在这种情况下,我们能够访问到namenode机器，namenode会给我们数据所在机器的IP地址供我们访问数据传输服务，但是当写数据的时候，NameNode和DataNode是通过内网通信的，返回的是datanode内网的IP,我们无法根据该IP访问datanode服务器。将默认的通过IP访问，改为通过域名方式访问。
//    主机是hadoop000,端口是9000
//    对全国高校院校类型进行分析，结果保存至本地/root/major/type/part-r-00000(数据格式：办学类型 数量)
//    对全国高校院校类型进行分析，结果保存至本地/root/major/type/part-r-00000(数据格式：办学类型 数量)


    val conf = new SparkConf().setMaster("local").setAppName("major_type")
    val sc = new SparkContext(conf)
    conf.set("dfs.client.use.datanode.hostname", "true")
    // 设置core-site.xml中的属性fs.defaultFS和属性值，注意主机名必须和设置的hosts主机名一致
    conf.set("fs.defaultFS", "hdfs://hadoop000:9000")
    val school = sc.textFile("hdfs://hadoop000:9000/major/school/part-m-00000")
    val professional = sc.textFile("hdfs://hadoop000:9000/major/professional/part-m-00000")
    val school1 = school.map(line => line.split(",")).map(line => (line(6), 1)).reduceByKey(_ + _)
    school1.saveAsTextFile("/root/major/type2/part-r-00000")
    sc.stop()

  }
}
