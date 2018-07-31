package com.lansalo.jmh

import com.lansalo.Target._
import org.openjdk.jmh.annotations._

class TargetPerformance {

  import Scopes._

  def testMapOnFunctionsAndList(state: BenchmarkState): Unit = {
    mapOnFunctionsAndList(state.title, state.slice, state.funcs)
  }

  def testFoldOnFunctionsList(state: BenchmarkState): Unit = {
    foldOnFunctionsList(state.title, state.slice, state.funcs)
  }

}

object Scopes {

  import com.lansalo.HashingFunctions.hashingFunctions

  val novelTitle = "The Persecution and Assassination of Jean-Paul Marat as Performed by the Inmates of the Asylum of Charenton Under the Direction of the Marquis de Sade"

  @State(Scope.Benchmark)
  class BenchmarkState {
    val funcs: List[String => Int] = hashingFunctions(200)
    val title: String = novelTitle
    val slice: Int = 4
  }
}
