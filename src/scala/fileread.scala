package scala


object fileread {
  def main(args: Array[String]) {
    val mylist = Array(1, 2, 3, 4)
    //    for (i<-mylist){
    //      println(i)
    //    }
    var sum = 0
    for (i <- 0 to mylist.length - 1) {
      sum += mylist(i)
    }
    //    println(sum)
    var max = mylist(0);
    for (i <- 1 to (mylist.length - 1)) {
      if (mylist(i) > max) {
        max = mylist(i)
      }
    }
    //    println(max)

    //    var z:Array[String] = new Array[String](3)
    var z = new Array[String](4) //better
    z(0) = "zyc"
    z(1) = "sdsd"
    for (i <- z) {
      println(i)
    }

  }

}
