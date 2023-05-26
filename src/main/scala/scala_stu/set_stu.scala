package scala

object set_stu {
  def main(args: Array[String]): Unit = {
//    集合
    var set1 = Set(1, 2, 3, 4)
    var set2 = Set(3, 4, 5, 6)
    println(set1++set2) // 并集
    println(set1--set2) // 差集
    println(set1&set2) // 交集
    println(set1+7) // 添加元素
    println(set1-1) // 删除元素

    //    iterator
    var iterator = Iterator("z", "y", "c")
    while (iterator.hasNext){
      println(iterator.next())
    }
//    添加元素 相当于连接
    iterator.++=(Iterator("a","b"))
    iterator.foreach(println)
    //    map 可变集合
    var map: Map[String, Int] = Map("a" -> 1, "b" -> 2, "c" -> 3)
    var stu1:Map[String,List[Int]] = Map()
    stu1+=("zyc"->List(100,92))
    var map2:Map[String,Map[String,List[Int]]] = Map() //复杂的map类型
    map2+=("2020"->Map("zs"->List(99,12),"ls"->List(12,13)))
    println(map2)

//   去出成绩的第一个值

//    map+=("d"->4)
//    map-=("a")
//    map.foreach(println)
//    for(keys <- map.keys){
//      println(keys+":"+map(keys))
//    }
//    map.foreach(x=>println(x._1+"价格:"+x._2))
//    map.keys.foreach(x=>println(x+":"+map(x)))

// tuple 元组
    val tuple1 = (1,2,3,4,5,6,7,8,9,10)
    println(tuple1._1)
//    zip函数 压缩
    val province = Array("安徽","江苏","浙江","福建")
    val city = Array("合肥","南京","杭州","福州")
    val zip1:Array[(String,String)] = province.zip(city)
    zip1.foreach(println)
    zip1.foreach(x=>println(x._1+"省会:"+x._2))

//    val pc = province.zip(city)
//    println(pc.toBuffer)

//    有一个数组，a1 = 。。
    var a1 = Array("zs","ls","ww")
    val scoce =Array(List(123,13,12),List(123,13,12),List(123,13,12))
    val zip3 =a1.zip(scoce)
    println(zip3.toBuffer)
    zip3.foreach(x=>println(x._1+"成绩:"+x._2.toBuffer))

//    var a2 = Array(100,90,80,70)
//    var a3 = Array(1,2,3,4)
//    val zip2 = a1.zip(a2)
//    println(zip2.toBuffer)
//    a3是二维数组，与a1进行压缩
    var a4 = Array(List(100,90,80,70),List(1,2,3,4),List(1,2,3,4))
    var arr2 = Array.ofDim[Int](3,3)
    for (i <- 0 to 2){
      for (j <- 0 to 2 ){
        arr2(i)(j) = i+j
      }
    }
    var zip2 = a1.zip(arr2)
    zip2.foreach(x=>println(x._1+"成绩:"+x._2.toBuffer))










  }
}
