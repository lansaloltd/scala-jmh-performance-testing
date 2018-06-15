package com.lansalo

import scala.util.hashing.MurmurHash3

object HashingFunctions {

  def hashingFunctions(n: Int): List[String => Int] = {
    (0 until n).toList.map(x => (s: String) => MurmurHash3.stringHash(s + x))
  }

  def hashingFunctionsStandard(n: Int): List[String => Int] = {
    (0 until n).toList.map(x => (s: String) => (s + x).hashCode)
  }

  val slide: (String, Int) => List[String] = (str, n) => str.sliding(n).toList

}
