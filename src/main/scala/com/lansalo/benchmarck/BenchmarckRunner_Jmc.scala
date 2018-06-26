package com.lansalo.benchmarck

import org.openjdk.jmh.annotations._
import org.openjdk.jmh.results.format.ResultFormatType
import org.openjdk.jmh.runner.Runner
import org.openjdk.jmh.runner.options.{Options, OptionsBuilder}

object BenchmarckRunner_Jmc{

  // run sbt clean jmh:compile from terminal first.
  def main(args: Array[String]): Unit = {
    val opt: Options = new OptionsBuilder().include(classOf[FlightRecorderDump].getSimpleName)
      .forks(1).resultFormat(ResultFormatType.TEXT).build
    new Runner(opt).run
  }
}

@Warmup(iterations = 40)
@Measurement(iterations = 30)
@State(Scope.Benchmark)
private class FlightRecorderDump extends TargetPerformance {

  @Benchmark
  @Fork(value = 1, jvmArgsAppend = Array("-XX:+UnlockCommercialFeatures -XX:+FlightRecorder -XX:+UnlockDiagnosticVMOptions -XX:+DebugNonSafepoints -XX:FlightRecorderOptions=defaultrecording=true,dumponexit=true,dumponexitpath=/tmp/foldOnFunctionsAndTitle.jfr"))
  override def testFoldOnFunctionsAndTitle(state: Scopes.BenchmarkState): Unit = super.testFoldOnFunctionsAndTitle(state)

  @Benchmark
  @Fork(value = 1, jvmArgsAppend = Array("-XX:+UnlockCommercialFeatures -XX:+FlightRecorder -XX:+UnlockDiagnosticVMOptions -XX:+DebugNonSafepoints -XX:FlightRecorderOptions=defaultrecording=true,dumponexit=true,dumponexitpath=/tmp/foldOnFunctionsList.jfr"))
  override def testFoldOnFunctionsList(state: Scopes.BenchmarkState): Unit = super.testFoldOnFunctionsList(state)

  @Benchmark
  @Fork(value = 1, jvmArgsAppend = Array("-XX:+UnlockCommercialFeatures -XX:+FlightRecorder -XX:+UnlockDiagnosticVMOptions -XX:+DebugNonSafepoints -XX:FlightRecorderOptions=defaultrecording=true,dumponexit=true,dumponexitpath=/tmp/MapOnFunctionsAndList.jfr"))
  override def testMapOnFunctionsAndList(state: Scopes.BenchmarkState): Unit = super.testMapOnFunctionsAndList(state)

}


