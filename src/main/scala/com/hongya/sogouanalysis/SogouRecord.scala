package com.hongya.sogouanalysis

object SogouRecord {
//  def apply(str: String, str1: String, str2: String, toInt: Int, toInt1: Int, str3: String): SogouRecord = ???

  case class SogouRecord(
                          queryTime: String,
                          userId: String,
                          queryWords: String,
                          resultRank: Int,
                          clickRank: Int,
                          clickUrl: String
                        )

}

