package io.github.albrzykowski.muzk

import io.github.albrzykowski.muzk.Element
import io.github.albrzykowski.muzk.{RandomAdapter => Random}

object ElementFactory {
  def get(
      random: Random,
      withRests: Boolean,
      values: Seq[Int],
      pitches: Seq[String],
      pitch: Option[String]
  ): Element = {

    if (!withRests) {
      getNote(random, values, pitches, pitch)
    } else {
      if (random.nextBoolean) {
        getNote(random, values, pitches, pitch)
      } else {
        getRest(random, values)
      }
    }
  }

  private def getNote(random: Random, values: Seq[Int], pitches: Seq[String], pitch: Option[String]): Note = {

    pitch match {
      case Some(pitch) => {
        val value = values(random.nextInt(values.length))
        Note(value, pitch)
      }
      case None => {
        val value = values(random.nextInt(values.length))
        val pitch = pitches(random.nextInt(pitches.length))
        Note(value, pitch)
      }
    }
  }
  private def getRest(random: Random, values: Seq[Int]): Rest = {
    val value = values(random.nextInt(values.length))
    val element = Rest(value)
    element
  }
}
