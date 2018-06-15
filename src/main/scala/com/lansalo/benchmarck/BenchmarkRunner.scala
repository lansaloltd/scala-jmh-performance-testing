package com.lansalo.benchmarck

import org.openjdk.jmh.profile.{ClassloaderProfiler, HotspotMemoryProfiler}
import org.openjdk.jmh.results.format.ResultFormatType
import org.openjdk.jmh.runner.Runner
import org.openjdk.jmh.runner.options.{Options, OptionsBuilder}

object BenchmarkRunner {

  // run sbt clean jmh:compile from terminal first.
  def main(args: Array[String]): Unit = {
    val opt: Options = new OptionsBuilder().include(classOf[TargetPerformance].getSimpleName).addProfiler(classOf[HotspotMemoryProfiler])
      .forks(1).resultFormat(ResultFormatType.TEXT).build
    new Runner(opt).run
  }

}
