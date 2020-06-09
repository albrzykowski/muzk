package io.github.albrzykowski.muzk

import org.scalatest.funsuite.AnyFunSuite
import org.scalamock.scalatest.MockFactory
import org.scalatest._
import Matchers._
import io.github.albrzykowski.muzk.{RandomAdapter => Random}
import io.github.albrzykowski.muzk.sequence.RandomWalk

class RandomWalkTest extends FlatSpec with MockFactory {

  "generate" should "return sequence of based of random walk" in {
    val randomMock = mock[Random]
    val seq = List("A", "B", "C", "D")
    val length = 3

    val expected = List("D", "C", "B")
    val randomWalk = new RandomWalk(randomMock)

    // Adds B to result
    (randomMock.nextInt _).expects(seq.length).returning(1).once()
    // Adds C to result
    (randomMock.nextBoolean _).expects().returning(true).once()
    // Adds D to result
    (randomMock.nextBoolean _).expects().returning(true).once()

    val result = randomWalk.generate(seq, length)

    result should equal(expected)
  }

  "generate" should "return second element when reaches beginning of sequence" in {
    val randomMock = mock[Random]
    val seq = List("A", "B")
    val length = 2

    val expected = List("B", "A")
    val randomWalk = new RandomWalk(randomMock)

    // Adds A to result
    (randomMock.nextInt _).expects(seq.length).returning(0).once()

    val result = randomWalk.generate(seq, length)

    result should equal(expected)
  }

  "generate" should "return penultimate element when reaches end of sequence" in {
    val randomMock = mock[Random]
    val seq = List("A", "B")
    val length = 2

    val expected = List("A", "B")
    val randomWalk = new RandomWalk(randomMock)

    // Adds B to result
    (randomMock.nextInt _).expects(seq.length).returning(1).once()

    val result = randomWalk.generate(seq, length)

    result should equal(expected)
  }
}
