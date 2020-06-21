package io.github.albrzykowski.muzk.sequence

import org.scalatest.funsuite.AnyFunSuite
import org.scalamock.scalatest.MockFactory
import org.scalatest._
import Matchers._

class InverseSequenceTest extends FlatSpec with MockFactory {

  "generate" should "return inverse sequence" in {
    val seq = List("E", "F", "G", "C#", "F#", "D#", "G#", "D", "B", "C", "A", "A#")
    val expected = List("E", "D#", "C#", "G", "D", "F", "C", "F#", "A", "G#", "B", "A#")

    val inverse = new InverseSequence()

    val result = inverse.generate(seq)

    result should equal(expected)
  }

}
