package io.github.albrzykowski.muzk

import org.scalatest.funsuite.AnyFunSuite
import org.scalamock.scalatest.MockFactory
import org.scalatest._
import io.github.albrzykowski.muzk.{RandomAdapter => Random}
import io.github.albrzykowski.muzk.RandomWalk

class MainTest extends FlatSpec with MockFactory {
  "randomStep" should "return backward step if randomized boolean value is false" in {
    val mockRandom = mock[Random]

    (mockRandom.nextBoolean _).expects().returning(false).once()
    assert(RandomWalk.randomStep(mockRandom) == -1)
  }
  "randomStep" should "return forward step if randomized boolean value is true" in {
    val mockRandom = mock[Random]

    (mockRandom.nextBoolean _).expects().returning(true).once()
    assert(RandomWalk.randomStep(mockRandom) == 1)
  }
  "walk" should "return previous element of sequence for backward" in {
    val current = 2
    val seq = Seq(1, 2, 3)
    val mockRandom = mock[Random]

    (mockRandom.nextBoolean _).expects().returning(false).once()
    assert(RandomWalk.walk(current, seq, mockRandom) == 1)
  }
  "walk" should "return next element of sequence for forward" in {
    val current = 2
    val seq = Seq(1, 2, 3)
    val mockRandom = mock[Random]

    (mockRandom.nextBoolean _).expects().returning(true).once()
    assert(RandomWalk.walk(current, seq, mockRandom) == 3)
  }
  "walk" should "return penultimate element of sequence if current element is the last one" in {
    val current = 3
    val seq = Seq(1, 2, 3)
    val mockRandom = mock[Random]

    assert(RandomWalk.walk(current, seq, mockRandom) == 2)
  }
  "walk" should "return second element of sequence if current element is the first one" in {
    val current = 1
    val seq = Seq(1, 2, 3)
    val mockRandom = mock[Random]

    assert(RandomWalk.walk(current, seq, mockRandom) == 2)
  }
}
