package com.lansalo.benchmarck

import java.util.concurrent.TimeUnit

import org.openjdk.jmh.annotations.Mode.{AverageTime, Throughput}
import org.openjdk.jmh.annotations._
import com.lansalo.Target._

@BenchmarkMode(Array(Throughput))
@OutputTimeUnit(TimeUnit.SECONDS)
@Warmup(iterations = 10)
@Measurement(iterations = 10)
@State(Scope.Benchmark)
class TargetPerformance {

  import Scopes._

  @Benchmark
  @Fork(value = 1, jvmArgsAppend = Array("-XX:+UseG1GC"))
  def testMapOnFunctionsAndList(state: BenchmarkState): Unit = {
    mapOnFunctionsAndList(state.title, state.slice, state.funcs)
  }

  @Benchmark
  @Fork(value = 1, jvmArgsAppend = Array("-XX:+UseG1GC"))
  def testFoldOnFunctionList(state: BenchmarkState): Unit = {
    foldOnFunctionList(state.title, state.slice, state.funcs)
  }

  @Benchmark
  @Fork(value = 1, jvmArgsAppend = Array("-XX:+UseG1GC"))
  def testFoldOnFinctionAndTitle(state: BenchmarkState): Unit = {
    foldOnFinctionAndTitle(state.title, state.slice, state.funcs)
  }

  @Benchmark
  @Fork(value = 1)
  def testFoldOnFunctionListWithoutG1(state: BenchmarkState): Unit = {
    foldOnFunctionList(state.title, state.slice, state.funcs)
  }

  @Benchmark
  @BenchmarkMode(Mode.AverageTime)
  @OutputTimeUnit(TimeUnit.MICROSECONDS)
  @Fork(value = 1)
  def testFoldOnFunctionListAvg(state: BenchmarkState): Unit = {
    foldOnFunctionList(state.title, state.slice, state.funcs)
  }

  @Benchmark
  @BenchmarkMode(Array(Mode.Throughput, Mode.AverageTime, Mode.SampleTime, Mode.SingleShotTime))
  @OutputTimeUnit(TimeUnit.MICROSECONDS)
  @throws[InterruptedException]
  @Fork(value = 1)
  def testFoldOnFunctionListAll(state: BenchmarkState): Unit = {
    foldOnFunctionList(state.title, state.slice, state.funcs)
  }


}

object Scopes {

  val novelTitle = "The Persecution and Assassination of Jean-Paul Marat as Performed by the Inmates of the Asylum of Charenton Under the Direction of the Marquis de Sade"

  @State(Scope.Benchmark)
  class BenchmarkState {
    val funcs: List[String => Int] = hashingFunctions(100)
    val title: String = novelTitle
    val slice: Int = 4
  }

  @State(Scope.Benchmark)
  class BenchmarkStateWithStream {
    val funcs: Stream[String => Int] = hashingFunctions(100).toStream
    val title: String = novelTitle
    val slice: Int = 4
  }

  @State(Scope.Benchmark)
  class BenchmarkStateWithHash {
    val funcs: List[String => Int] = hashingFunctionsStandard(100)
    val title: String = novelTitle
    val slice: Int = 4
  }
}
