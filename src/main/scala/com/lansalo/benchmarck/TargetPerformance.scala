package com.lansalo.benchmarck

import java.util.concurrent.TimeUnit

import org.openjdk.jmh.annotations.Mode.{AverageTime, Throughput}
import org.openjdk.jmh.annotations._
import com.lansalo.Target._

@BenchmarkMode(Array(Throughput))
@OutputTimeUnit(TimeUnit.SECONDS)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
@State(Scope.Benchmark)
class TargetPerformance {

  import Scopes._
  //-XX:+PrintGCDetails -> [info] TargetPerformance.underTest1  thrpt    5  229.543 ± 189.899  ops/s
  //-XX:+PrintGCDetails -XX:NewRatio=1 -> [info] TargetPerformance.underTest1  thrpt    5  219.292 ± 105.881  ops/s
  @Benchmark
  @Fork(value = 1, jvmArgsAppend = Array("-XX:+PrintGCDetails", "-XX:NewRatio=1", "-XX:GCTimeRatio=2", "-XX:NewSize=887031808"))
  def underTest1(state: BenchmarkState): Unit = {
    println("NewSize ==========> " + System.getProperties)

    mapOnFunctionsAndList(state.title, state.slice, state.funcs)
  }

  // -XX:GCTimeRatio=19
  // "-XX:+AggressiveOpts", "-XX:+UnlockDiagnosticVMOptions", "-XX:+UnlockExperimentalVMOptions", "-XX:+PrintFlagsFinal", "-XX:+PrintFlagsWithComments", "-version",


}

object Scopes {

  @State(Scope.Benchmark)
  class BenchmarkState {
    val funcs: List[String => Int] = hashingFunctions(100)
    val title: String = "dfsdgfdgjn jkfdg jkdfgjdfkg dfjk gjdfkg jkdf gjdf gkdfj gjkdf gjkdf gjkdf gjkdf k"
    val slice: Int = 4
  }

  @State(Scope.Benchmark)
  class BenchmarkStateWithStream {
    val funcs: Stream[String => Int] = hashingFunctions(100).toStream
    val title: String = "dfsdgfdgjn jkfdg jkdfgjdfkg dfjk gjdfkg jkdf gjdf gkdfj gjkdf gjkdf gjkdf gjkdf k"
    val slice: Int = 4
  }

  @State(Scope.Benchmark)
  class BenchmarkStateWithHash {
    val funcs: List[String => Int] = hashingFunctionsStandard(100)
    val title: String = "dfsdgfdgjn jkfdg jkdfgjdfkg dfjk gjdfkg jkdf gjdf gkdfj gjkdf gjkdf gjkdf gjkdf k"
    val slice: Int = 4
  }
}
