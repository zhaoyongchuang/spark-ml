package scala

import java.io.PrintWriter

object kelihua {
  def main(args: Array[String]): Unit = {
    mywrite("hello")("D:\\test.txt")
// 柯里化
    val fp2 = mywrite("hello spark")
//    function split into two parts
    fp2("D:\\test2.txt")
    fp2("D:\\test3.txt")

  }
  def myf1(x:Int)=(y:Int)=>x+y
  def mywrite(content:String)=(path:String)=>{
    var wf1 = new PrintWriter(path)
//    wf1.write(content)
    wf1.println(content)
    wf1.close()
  }

}
