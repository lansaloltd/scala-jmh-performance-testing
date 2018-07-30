package com.lansalo.benchmarck

import java.util.concurrent.TimeUnit

import org.openjdk.jmh.annotations.Mode.{AverageTime, Throughput}
import org.openjdk.jmh.annotations._
import com.lansalo.Target._

/*@BenchmarkMode(Array(Throughput))
@OutputTimeUnit(TimeUnit.SECONDS)
@Warmup(iterations = 40)
@Measurement(iterations = 30)
@State(Scope.Benchmark)*/
class TargetPerformance {

  import Scopes._

  //@Benchmark
  //@Fork(value = 1/*, jvmArgsAppend = Array("-XX:+UseG1GC")*/)
  def testMapOnFunctionsAndList(state: BenchmarkState): Unit = {
    mapOnFunctionsAndList(state.title, state.slice, state.funcs) // 0.911 ops/s [Average]
  }

  //@Benchmark
  //@Fork(value = 1/*, jvmArgsAppend = Array("-XX:+UseG1GC")*/)
  def testFoldOnFunctionsList(state: BenchmarkState): Unit = {
    foldOnFunctionsList(state.title, state.slice, state.funcs) // 1.418 ops/s [Average]
  }

/*

  @Benchmark
  @Fork(value = 1)
  def testFoldOnFunctionListWithoutG1(state: BenchmarkState): Unit = {
    foldOnFunctionList(state.title, state.slice, state.funcs) // 19.584 ops/s [Average]
  }

  @Benchmark
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MICROSECONDS)
  @Fork(value = 1)
  def testFoldOnFunctionListAvg(state: BenchmarkState): Unit = {
    foldOnFunctionList(state.title, state.slice, state.funcs)
  }

  @Benchmark
  @BenchmarkMode(Array(Mode.AverageTime, Mode.SampleTime, Mode.SingleShotTime))
  @OutputTimeUnit(TimeUnit.MICROSECONDS)
  @throws[InterruptedException]
  @Fork(value = 1)
  def testFoldOnFunctionListAll(state: BenchmarkState): Unit = {
    foldOnFunctionList(state.title, state.slice, state.funcs)
  }
*/


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
