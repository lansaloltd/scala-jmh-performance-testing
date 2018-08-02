package com.lansalo.jmh.benchmark

import java.util.concurrent.TimeUnit

import com.lansalo.jmh.{Scopes, TargetPerformance}
import org.openjdk.jmh.annotations._
import org.openjdk.jmh.annotations.BenchmarkMode
import org.openjdk.jmh.annotations.Mode
import org.openjdk.jmh.results.format.ResultFormatType
import org.openjdk.jmh.runner.Runner
import org.openjdk.jmh.runner.options.{Options, OptionsBuilder}

object BenchmarkRunner_ThroughputBenchmark {
  // run sbt clean jmh:compile from terminal first.
  def main(args: Array[String]): Unit = {
    val opt: Options = new OptionsBuilder().include(classOf[ThroughputBenchmark].getSimpleName)
      .resultFormat(ResultFormatType.TEXT).build
    new Runner(opt).run
  }
}

@OutputTimeUnit(TimeUnit.SECONDS)
@Warmup(iterations = 30)
@Measurement(iterations = 30)
@State(Scope.Benchmark)
private class ThroughputBenchmark extends TargetPerformance {

  @Benchmark
  @BenchmarkMode(Array(Mode.Throughput, Mode.AverageTime))
  @Fork(value = 1)
  override def testFoldOnFunctionsList(state: Scopes.BenchmarkState): Unit = super.testFoldOnFunctionsList(state)

  @Benchmark
  @BenchmarkMode(Array(Mode.Throughput, Mode.AverageTime))
  @Fork(value = 1)
  override def testMapOnFunctionsAndList(state: Scopes.BenchmarkState): Unit = super.testMapOnFunctionsAndList(state)

}
