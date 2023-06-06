package streaming_stu

object per_5s_file {
  def main(args: Array[String]): Unit = {
    //    每个五秒写文件一次
    //    写到D:/logs/目录下
        for(i <- 1 to 10){
          val file = new java.io.File("D:/logs/"+i+".txt")
          val writer = new java.io.PrintWriter(file)
          writer.write("hello world")
          writer.close()
          Thread.sleep(5000)
        }
  }
}
