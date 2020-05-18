package io.github.albrzykowski.muzk

import org.scalatest.funsuite.AnyFunSuite
import org.scalamock.scalatest.MockFactory
import org.scalatest._
import Matchers._
import io.github.albrzykowski.muzk.{RandomAdapter => Random}
import io.github.albrzykowski.muzk.RandomWalk

class MainTest extends FlatSpec with MockFactory {

  "randomStep" should "return backward step if randomized boolean value is false" in {
    val mockRandom = mock[Random]
    val randomWalk = new RandomWalk(mockRandom)

    (mockRandom.nextBoolean _).expects().returning(false).once()
    assert(randomWalk.randomStep() == -1)
  }
  "randomStep" should "return forward step if randomized boolean value is true" in {
    val mockRandom = mock[Random]
    val randomWalk = new RandomWalk(mockRandom)

    (mockRandom.nextBoolean _).expects().returning(true).once()
    assert(randomWalk.randomStep() == 1)
  }
  "walk" should "return previous element of sequence for backward" in {
    val current = 2
    val seq = Seq(1, 2, 3)
    val mockRandom = mock[Random]
    val randomWalk = new RandomWalk(mockRandom)

    (mockRandom.nextBoolean _).expects().returning(false).once()
    assert(randomWalk.walk(current, seq) == 1)
  }
  "walk" should "return next element of sequence for forward" in {
    val current = 2
    val seq = Seq(1, 2, 3)
    val mockRandom = mock[Random]
    val randomWalk = new RandomWalk(mockRandom)

    (mockRandom.nextBoolean _).expects().returning(true).once()
    assert(randomWalk.walk(current, seq) == 3)
  }
  "walk" should "return penultimate element of sequence if current element is the last one" in {
    val current = 3
    val seq = Seq(1, 2, 3)
    val mockRandom = mock[Random]
    val randomWalk = new RandomWalk(mockRandom)

    assert(randomWalk.walk(current, seq) == 2)
  }
  "walk" should "return second element of sequence if current element is the first one" in {
    val current = 1
    val seq = Seq(1, 2, 3)
    val mockRandom = mock[Random]
    val randomWalk = new RandomWalk(mockRandom)

    assert(randomWalk.walk(current, seq) == 2)
  }

  "generate" should "return random sequence of given length" in {
    val values = Seq(1, 2)
    val pitches = Seq("C", "D", "E", "F")
    val pieceLength = 5
    val mockRandom = mock[Random]
    val randomWalk = new RandomWalk(mockRandom)
    val withRests = true
    (mockRandom.nextInt _)
      .expects(ElementType.maxId)
      .returning(1)
      .anyNumberOfTimes()
    (mockRandom.nextInt _)
      .expects(values.length)
      .returning(1)
      .anyNumberOfTimes()
    (mockRandom.nextInt _)
      .expects(pitches.length)
      .returning(1)
      .anyNumberOfTimes()
    (mockRandom.nextBoolean _).expects().returning(false).anyNumberOfTimes()

    val result = randomWalk.generate(values, pitches, pieceLength, withRests)

    assert(result.length == pieceLength)
  }

  "generate" should "properly adds rests" in {
    val values = Seq(1, 2)
    val pitches = Seq("C", "D", "E", "F")
    val pieceLength = 2
    val withRests = true
    val expected = List("Send, 10{Right}", "Send, 20{Right}")
    val mockRandom = mock[Random]
    val randomWalk = new RandomWalk(mockRandom)
    (mockRandom.nextInt _)
      .expects(ElementType.maxId)
      .returning(1)
      .anyNumberOfTimes()
    (mockRandom.nextInt _)
      .expects(values.length)
      .returning(1)
      .anyNumberOfTimes()
    (mockRandom.nextInt _)
      .expects(pitches.length)
      .returning(1)
      .anyNumberOfTimes()
    (mockRandom.nextBoolean _).expects().returning(false).anyNumberOfTimes()

    val result = randomWalk.generate(values, pitches, pieceLength, withRests)
    result should equal(expected)
  }
  "generate" should "properly adds notes" in {
    val values = Seq(1, 2)
    val pitches = Seq("C", "D", "E", "F")
    val pieceLength = 2
    val withRests = true
    val expected = List("Send, 2C{Right}", "Send, 1D{Right}")
    val mockRandom = mock[Random]
    val randomWalk = new RandomWalk(mockRandom)
    (mockRandom.nextInt _)
      .expects(ElementType.maxId)
      .returning(0)
      .anyNumberOfTimes()
    (mockRandom.nextInt _)
      .expects(values.length)
      .returning(1)
      .anyNumberOfTimes()
    (mockRandom.nextInt _)
      .expects(pitches.length)
      .returning(1)
      .anyNumberOfTimes()
    (mockRandom.nextBoolean _).expects().returning(false).anyNumberOfTimes()

    val result = randomWalk.generate(values, pitches, pieceLength, withRests)
    result should equal(expected)
  }
  "generate" should "properly adds notes from chromatic scale" in {
    val values = Seq(1, 2)
    val pitches = Seq("C", "C#", "D", "D#")
    val pieceLength = 2
    val withRests = true
    val expected = List("Send, 2C{Right}", "Send, 1C{Up}{Right}")
    val mockRandom = mock[Random]
    val randomWalk = new RandomWalk(mockRandom)
    (mockRandom.nextInt _)
      .expects(ElementType.maxId)
      .returning(0)
      .anyNumberOfTimes()
    (mockRandom.nextInt _)
      .expects(values.length)
      .returning(1)
      .anyNumberOfTimes()
    (mockRandom.nextInt _)
      .expects(pitches.length)
      .returning(1)
      .anyNumberOfTimes()
    (mockRandom.nextBoolean _).expects().returning(false).anyNumberOfTimes()

    val result = randomWalk.generate(values, pitches, pieceLength, withRests)
    result should equal(expected)
  }
}
