package com.lansalo

import org.scalatest.FunSuite
import com.lansalo.HashingFunctions._
import com.lansalo.Target._

class TargetSpec extends FunSuite {

  import TestFixture._

  test("mapOnFunctionsAndList should return the expected result for a given input") {
    assert(mapOnFunctionsAndList(Input, 4, hashingFunctions(10)).sorted === ExpectedResult)
  }

  test("foldOnFunctionList should return the expected result for a given input") {
    assert(foldOnFunctionsList(Input, 4, hashingFunctions(10)).sorted === ExpectedResult)
  }

}

private object TestFixture {

  val Input = "This is a sample title"
  val ExpectedResult = List(-2047663159, -1991427860, -1771568845, -1551517389, -2139030538, -2062603705, -1368639057, -1763779525, -2000398266, -2134651155).sorted

}
