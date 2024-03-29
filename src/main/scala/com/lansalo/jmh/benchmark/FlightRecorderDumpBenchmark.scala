package com.lansalo.jmh.benchmark

import com.lansalo.jmh.{Scopes, TargetPerformance}
import org.openjdk.jmh.annotations._
import org.openjdk.jmh.results.format.ResultFormatType
import org.openjdk.jmh.runner.Runner
import org.openjdk.jmh.runner.options.{Options, OptionsBuilder}

object BenchmarkRunner_FlightRecorder {
  // run sbt clean jmh:compile from terminal first.
  def main(args: Array[String]): Unit = {
    val opt: Options = new OptionsBuilder().include(classOf[FlightRecorderDump].getSimpleName)
      .resultFormat(ResultFormatType.TEXT).build
    new Runner(opt).run
  }
}

@Warmup(iterations = 30)
@Measurement(iterations = 30)
@State(Scope.Benchmark)
private class FlightRecorderDump extends TargetPerformance {

  @Benchmark
  @Fork(value = 1, jvmArgsAppend = Array("-XX:+UnlockCommercialFeatures",
      "-XX:+FlightRecorder", "-XX:+UnlockDiagnosticVMOptions", "-XX:+DebugNonSafepoints",
      "-XX:FlightRecorderOptions=defaultrecording=true,dumponexit=true,dumponexitpath=/tmp/foldOnFunctionsList.jfr"))
  override def testFoldOnFunctionsList(state: Scopes.BenchmarkState): Unit = super.testFoldOnFunctionsList(state)

  @Benchmark
  @Fork(value = 1, jvmArgsAppend = Array("-XX:+UnlockCommercialFeatures",
    "-XX:+FlightRecorder", "-XX:+UnlockDiagnosticVMOptions", "-XX:+DebugNonSafepoints",
    "-XX:FlightRecorderOptions=defaultrecording=true,dumponexit=true,dumponexitpath=/tmp/mapOnFunctionsAndList.jfr"))
  override def testMapOnFunctionsAndList(state: Scopes.BenchmarkState): Unit = super.testMapOnFunctionsAndList(state)

}


