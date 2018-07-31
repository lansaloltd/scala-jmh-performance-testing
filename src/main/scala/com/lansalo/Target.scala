package com.lansalo

import com.lansalo.HashingFunctions.slide

object Target {

  // candidate 1
  def mapOnFunctionsAndList(input: String, slice: Int, funcs: List[String => Int]): List[Int] = {
    funcs.map(f => slide(input, slice).map(f(_)).min)
  }

  // candidate 2
  def foldOnFunctionsList(input: String, slice: Int, funcs: List[String => Int]): List[Int] = {
    funcs.foldLeft(List.empty[Int])((acc, func) => {
      slide(input, slice).map(str => func(str)).min :: acc
    })
  }
}
