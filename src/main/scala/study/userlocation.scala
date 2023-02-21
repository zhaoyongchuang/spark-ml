package study

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object userlocation {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("userlocation").setMaster("local[*]")
    val sc = new SparkContext(conf)
    val rdd0 = sc.textFile("src/main/scala/study/data/user.log").map(
      line => {
        val fields = line.split(",")
        val phone = fields(0)
        val time = fields(1).toLong
        val id = fields(2)
        val eventtype = fields(3).toLong
        val timelong =  if (eventtype == 1) -time else time
        ((phone,id), timelong)
      }
    )
//    println(rdd0.collect().toBuffer)
    val reducebykey1:RDD[((String, String), Long)] = rdd0.reduceByKey(_+_)
//    println(reducebykey1.collect().toList)
    val id_phone:RDD[(String,(String,Long))] = reducebykey1.map{
      line =>{
        val phone = line._1._1
        val id = line._1._2
        val timelong = line._2
        (id,(phone,timelong))
      }
    }
    println(id_phone.collect().toBuffer)

    val sc1 = sc.textFile("src/main/scala/study/data/loc.log").map(
      line => {
        val fields = line.split(",")
        val id :String= fields(0)
        val x :String= fields(1)
        val y :String= fields(2)
        (id,(x,y))
      }
    )
    println(sc1.collect().toBuffer)

    val join_id = id_phone.join(sc1)
    println(join_id.collect().toBuffer)

    val id_phone_time_xy = join_id.map(line =>
      {
       val id = line._1
       val phone = line._2._1._1
        val time = line._2._1._2
        val xy = line._2._2
        (id,((phone,time),xy))
      }
    )
    println(id_phone_time_xy.collect().toBuffer)
    val groupbyphone1 = id_phone_time_xy.groupBy(_._2._1._1)

    val groupbyphone2:RDD[(String,Iterable[(String,((String,Long),(String,String)))])] = id_phone_time_xy.groupBy(_._2._1._1)
    println(groupbyphone1.collect().toBuffer)
    println(groupbyphone2.collect().toBuffer)
    val sort_time = groupbyphone2.mapValues(
      it => {
        it.toList.sortBy(_._2._1._2).reverse.take(2)
      }
    )
    println(sort_time.collect().toBuffer)





  }
}
