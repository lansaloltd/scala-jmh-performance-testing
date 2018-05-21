package com.lansalo

import scala.util.hashing.MurmurHash3

object Target {

  val slide: (String, Int) => List[String] = (str, n) => str.sliding(n).toList

  def mapOnFunctionsAndList(title: String, slice: Int, funcs: List[String => Int]): List[Int] = {
    funcs.map(f => {
      slide(title, slice).map(f(_)).min
    })
  }

  def underTest2(title: String, slice: Int, funcs: List[String => Int]): List[Int] = {
    funcs.foldLeft(List.empty[Int])((acc, func) => {
      slide(title, slice).map(str => func(str)).min :: acc
    })
  }


  def underTest6(title: String, slice: Int, funcs: List[String => Int]): List[Int] = {
    funcs.foldLeft(List.empty[Int])((acc, func) => {
      slide(title, slice).par.map(str => func(str)).min :: acc
    })
  }

  def underTest7(title: String, slice: Int, funcs: List[String => Int]): List[Int] = {
    funcs.par.map(f => {
      slide(title, slice).par.map(f(_)).min
    }).toList
  }

  def underTest9(title: String, slice: Int, funcs: List[String => Int]): List[Int] = {
    funcs.foldLeft(List.empty[Int])((acc, func) => {
      slide(title, slice).foldLeft(Int.MaxValue)((tempMin, str) => {func(str).min(tempMin)}) :: acc
    })
  }

  def hashingFunctions(n: Int): List[String => Int] = {
    (0 until n).toList.map(x => (s: String) => MurmurHash3.stringHash(s + x))
  }

  def hashingFunctionsStandard(n: Int): List[String => Int] = {
    (0 until n).toList.map(x => (s: String) => (s + x).hashCode)
  }

}
