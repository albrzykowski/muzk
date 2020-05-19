package io.github.albrzykowski.muzk

import org.scalatest.funsuite.AnyFunSuite
import org.scalamock.scalatest.MockFactory
import org.scalatest._
import Matchers._
import io.github.albrzykowski.muzk.{RandomAdapter => Random}
import io.github.albrzykowski.muzk.RandomWalk
import io.github.albrzykowski.muzk.ElementFactory

class ElementFactoryTest extends FlatSpec with MockFactory {

  "get" should "return Note when withRests is false" in {
    val randomMock = mock[Random]
    val withRests = false
    val values = Seq(1, 2)
    val pitches = Seq("C", "D", "E", "F", "G", "A", "B")
    val pitch = None
    val expected = Note(2, "B")

    (randomMock.nextInt _).expects(values.length).returning(values.length - 1).once()
    (randomMock.nextInt _).expects(pitches.length).returning(pitches.length - 1).once()

    val result = ElementFactory.get(randomMock, withRests, values, pitches, pitch)

    result should equal(expected)
  }

  "get" should "return Note when Note is randomized and pitch is given" in {
    val randomMock = mock[Random]
    val withRests = true
    val values = Seq(1, 2)
    val pitches = Seq("C", "D", "E", "F", "G", "A", "B")
    val pitch = Option("F")
    val expected = Note(2, "F")

    (randomMock.nextBoolean _).expects().returning(true).once()
    (randomMock.nextInt _).expects(values.length).returning(values.length - 1).once()
    val result = ElementFactory.get(randomMock, withRests, values, pitches, pitch)

    result should equal(expected)
  }

  "get" should "return Rest when Rest is randomized" in {
    val randomMock = mock[Random]
    val withRests = true
    val values = Seq(1, 2)
    val pitches = Seq("C", "D", "E", "F", "G", "A", "B")
    val pitch = None
    val expected = Rest(2)

    (randomMock.nextBoolean _).expects().returning(false).once()
    (randomMock.nextInt _).expects(values.length).returning(values.length - 1).once()
    val result = ElementFactory.get(randomMock, withRests, values, pitches, pitch)

    result should equal(expected)
  }

}
