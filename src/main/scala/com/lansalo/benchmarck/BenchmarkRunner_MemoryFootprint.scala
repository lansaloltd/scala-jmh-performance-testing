package com.lansalo.benchmarck

import java.util.concurrent.TimeUnit

import org.openjdk.jmh.annotations.Mode.Throughput
import org.openjdk.jmh.annotations._
import org.openjdk.jmh.profile.HotspotMemoryProfiler
import org.openjdk.jmh.results.format.ResultFormatType
import org.openjdk.jmh.runner.Runner
import org.openjdk.jmh.runner.options.{Options, OptionsBuilder}

object BenchmarkRunner_MemoryFootprint {

  // run sbt clean jmh:compile from terminal first.
  def main(args: Array[String]): Unit = {
    val opt: Options = new OptionsBuilder().include(classOf[MemoryFootprint].getSimpleName).addProfiler(classOf[HotspotMemoryProfiler])
      .forks(1).resultFormat(ResultFormatType.TEXT).build
    new Runner(opt).run
  }
}

@BenchmarkMode(Array(Throughput))
@OutputTimeUnit(TimeUnit.SECONDS)
@Warmup(iterations = 30)
@Measurement(iterations = 30)
@State(Scope.Benchmark)
private class MemoryFootprint extends TargetPerformance {

  @Benchmark
  @Fork(value = 1)
  override def testFoldOnFunctionsList(state: Scopes.BenchmarkState): Unit = super.testFoldOnFunctionsList(state)

  @Benchmark
  @Fork(value = 1)
  override def testMapOnFunctionsAndList(state: Scopes.BenchmarkState): Unit = super.testMapOnFunctionsAndList(state)

}
