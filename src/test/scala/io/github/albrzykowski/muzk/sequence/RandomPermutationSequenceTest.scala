package io.github.albrzykowski.muzk.sequence

import org.scalatest.funsuite.AnyFunSuite
import org.scalamock.scalatest.MockFactory
import org.scalatest._
import Matchers._
import io.github.albrzykowski.muzk.{RandomAdapter => Random}

class RandomPermutationSequenceTest extends FlatSpec with MockFactory {

  "generate" should "return random permutation" in {
    val randomMock = mock[Random]
    val seq = List("A", "B", "C", "D")
    val expected = List("D", "C", "B", "A")

    val randomPermutation = new RandomPermutationSequence(randomMock)

    (randomMock.shuffle[String] _).expects(seq).returning(expected).once()

    val result = randomPermutation.generate(seq)

    result should equal(expected)
  }
}
