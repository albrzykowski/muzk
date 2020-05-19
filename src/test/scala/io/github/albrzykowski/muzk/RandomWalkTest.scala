package io.github.albrzykowski.muzk

import org.scalatest.funsuite.AnyFunSuite
import org.scalamock.scalatest.MockFactory
import org.scalatest._
import Matchers._
import io.github.albrzykowski.muzk.{RandomAdapter => Random}
import io.github.albrzykowski.muzk.RandomWalk
import io.github.albrzykowski.muzk.ElementFactory

class RandomWalkTest extends FlatSpec with MockFactory {

  "generate" should "return sequence of Notes when rests generation is disabled" in {
    val randomMock = mock[Random]
    val withRests = false
    val values = Seq(1, 2)
    val pitches = Seq("A", "B", "C", "D")
    val pieceLength = 5

    val expected = List(Note(2, "D"), Note(2, "C"), Note(2, "B"), Note(2, "A"), Note(2, "B"))
    val randomWalk = new RandomWalk(randomMock)
    // D:
    (randomMock.nextInt _).expects(values.length).returning(values.length - 1).once()
    (randomMock.nextInt _).expects(pitches.length).returning(pitches.length - 1).once()
    // D:
    (randomMock.nextInt _).expects(values.length).returning(values.length - 1).once()
    (randomMock.nextInt _).expects(pitches.length).returning(pitches.length - 1).once()
    (randomMock.nextInt _).expects(values.length).returning(values.length - 1).once()
    // B:
    (randomMock.nextInt _).expects(values.length).returning(values.length - 1).once()
    (randomMock.nextInt _).expects(pitches.length).returning(pitches.length - 1).once()
    (randomMock.nextBoolean _).expects().returning(false).once()
    (randomMock.nextInt _).expects(values.length).returning(values.length - 1).once()
    // A:
    (randomMock.nextInt _).expects(values.length).returning(values.length - 1).once()
    (randomMock.nextInt _).expects(pitches.length).returning(pitches.length - 1).once()
    (randomMock.nextBoolean _).expects().returning(false).once()
    (randomMock.nextInt _).expects(values.length).returning(values.length - 1).once()
    // B:
    (randomMock.nextInt _).expects(values.length).returning(values.length - 1).once()
    (randomMock.nextInt _).expects(pitches.length).returning(pitches.length - 1).once()
    (randomMock.nextInt _).expects(values.length).returning(values.length - 1).once()

    val result = randomWalk.generate(values, pitches, pieceLength, withRests)

    result should equal(expected)
  }

  "generate" should "return sequence of Notes and Rests when rests generation is enabled" in {
    val randomMock = mock[Random]
    val withRests = true
    val values = Seq(1, 2)
    val pitches = Seq("A", "B", "C", "D")
    val pieceLength = 5

    val expected = List(Note(2, "D"), Rest(2), Rest(2), Rest(2), Rest(2))
    val randomWalk = new RandomWalk(randomMock)
    // D:
    (randomMock.nextInt _).expects(values.length).returning(values.length - 1).once()
    (randomMock.nextInt _).expects(pitches.length).returning(pitches.length - 1).once()
    // 0:
    (randomMock.nextBoolean _).expects().returning(false).once()
    (randomMock.nextInt _).expects(values.length).returning(values.length - 1).once()
    // 0:
    (randomMock.nextBoolean _).expects().returning(false).once()
    (randomMock.nextInt _).expects(values.length).returning(values.length - 1).once()
    // 0:
    (randomMock.nextBoolean _).expects().returning(false).once()
    (randomMock.nextInt _).expects(values.length).returning(values.length - 1).once()
    // 0:
    (randomMock.nextBoolean _).expects().returning(false).once()
    (randomMock.nextInt _).expects(values.length).returning(values.length - 1).once()

    val result = randomWalk.generate(values, pitches, pieceLength, withRests)

    result should equal(expected)
  }

}
