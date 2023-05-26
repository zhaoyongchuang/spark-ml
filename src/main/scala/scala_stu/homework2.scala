package scala

object homework2 {
  def main(args: Array[String]): Unit = {
    for(room <- 1 to 100){
      var state = false
      for(people<-1 to 100){
        if(room % people == 0){
          state = !state
        }
      }
      if (state) {
        println("第" + room + "开着")
      }

    }



  }

}
