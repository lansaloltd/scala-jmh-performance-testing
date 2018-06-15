package com.lansalo

import com.lansalo.HashingFunctions.slide

object Target {

  // candidate 1
  def mapOnFunctionsAndList(title: String, slice: Int, funcs: List[String => Int]): List[Int] = {
    funcs.map(f => slide(title, slice).map(f(_)).min)
  }

  // candidate 2
  def foldOnFunctionList(title: String, slice: Int, funcs: List[String => Int]): List[Int] = {
    funcs.foldLeft(List.empty[Int])((acc, func) => {
      slide(title, slice).map(str => func(str)).min :: acc
    })
  }

  // candidate 3
  def foldOnFinctionAndTitle(title: String, slice: Int, funcs: List[String => Int]): List[Int] = {
    funcs.foldLeft(List.empty[Int])((acc, func) => {
      slide(title, slice).foldLeft(Int.MaxValue)((tempMin, str) => {func(str).min(tempMin)}) :: acc
    })
  }

}
