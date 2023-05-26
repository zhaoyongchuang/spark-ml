package scala

object digui {
  def main(args: Array[String]): Unit = {
    //    递归学习
    def f(n: Int): Int = {
      if (n == 1) {
        1
      } else {
        n * f(n - 1)
      }
    }

    //    println(f(5))


    def fblq(n: Int): Int = {
      if (n == 1 || n == 2) {
        1
      }
      else {
        fblq(n - 1) + fblq(n - 2)
      }

    }

    //    println(fblq(6))
    //    铺砖问题
    def pz(n: Int): Int = {
      if (n == 1) {
        1
      }
      else if (n == 2) {
        2
      }
      else {
        pz(n - 1) + pz(n - 2)
      }

    }
    //    println(pz(10))
    //    外星生物繁殖问题：大生物每次繁殖三个小生物，小生物两天后变成大生物，问n天后有多少个大生物




  }
}
