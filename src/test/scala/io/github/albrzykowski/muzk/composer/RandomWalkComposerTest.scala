package io.github.albrzykowski.muzk.composer

import org.scalatest.funsuite.AnyFunSuite
import org.scalamock.scalatest.MockFactory
import org.scalatest._
import Matchers._
import io.github.albrzykowski.muzk.{RandomAdapter => Random}
import io.github.albrzykowski.muzk.sequence.RandomWalkSequence
import io.github.albrzykowski.muzk.composer.RandomWalkComposer

class RandomWalkComposerTest extends FlatSpec with MockFactory {

  "generate" should "return sequence of based of random walk" in {
    val randomMock = mock[Random]
    val randomWalkSequenceMock = mock[RandomWalkSequence]
    val pitches = List("C", "D", "E", "F", "G", "A", "B")
    val values = List("Whole note", "Half note")
    val types = List("Note", "Rest")
    val randomPitches = List("C", "B", "A")
    val randomValues = List("Whole note", "Half note", "Whole note")
    val randomTypes = List("Note", "Rest", "Note")
    val length = 3
    val expected = List(("A", "Whole note", "Note"), ("B", "Half note", "Rest"), ("C", "Whole note", "Note"))

    val randomWalkComposer = new RandomWalkComposer(randomWalkSequenceMock)

    (randomWalkSequenceMock.generate[String] _).expects(pitches, length).returning(randomPitches).once()
    (randomWalkSequenceMock.generate[String] _).expects(values, length).returning(randomValues).once()
    (randomWalkSequenceMock.generate[String] _).expects(types, length).returning(randomTypes).once()

    val result = randomWalkComposer.compose(pitches, values, types, length)

    result should equal(expected)
  }
}
