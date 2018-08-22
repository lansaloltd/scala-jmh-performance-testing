package com.lansalo.scalameter.benchmark

import java.io.File

import com.lansalo.HashingFunctions.hashingFunctions
import com.lansalo.Target._
import org.scalameter.persistence.SerializationPersistor
import org.scalameter.{Bench, Gen}

import scala.util.Random

object PerformanceBenchmark extends Bench.OfflineReport {

  override lazy val persistor = SerializationPersistor(new File("target/scalameter/performance/results"))

  val functionsGen: Gen[Int] = Gen.range("functionsNumber")(100, 1000, 100)
  val inputGen: Gen[Int] = Gen.range("imputLength")(20, 200, 20)

  val inputs: Gen[(String, List[String => Int])] = for {
    functionsNumber <- functionsGen
    stringLength <- inputGen
  } yield (Random.alphanumeric take stringLength mkString, hashingFunctions(functionsNumber))

  performance of "inputs" in {
    measure method "mapOnFunctionsAndList" in {
      using(inputs) in {
        param => mapOnFunctionsAndList(param._1, 4, param._2)
      }
    }
  }

  performance of "inputs" in {
    measure method "foldOnFunctionsList" in {
      using(inputs) in {
        param => foldOnFunctionsList(param._1, 4, param._2)
      }
    }
  }

}
