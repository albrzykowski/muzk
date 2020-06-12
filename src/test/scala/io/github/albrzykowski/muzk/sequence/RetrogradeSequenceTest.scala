package io.github.albrzykowski.muzk.sequence

import org.scalatest.funsuite.AnyFunSuite
import org.scalamock.scalatest.MockFactory
import org.scalatest._
import Matchers._
import io.github.albrzykowski.muzk.{RandomAdapter => Random}

class RetrogradeSequenceTest extends FlatSpec with MockFactory {

  "generate" should "return retragraded sequence" in {
    val seq = List("A", "B", "C", "D")
    val expected = List("D", "C", "B", "A")

    val retrogradePermutation = new RetrogradeSequence()

    val result = retrogradePermutation.generate(seq)

    result should equal(expected)
  }
}
