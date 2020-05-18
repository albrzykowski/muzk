package io.github.albrzykowski.muzk

import io.github.albrzykowski.muzk.Element
import io.github.albrzykowski.muzk.{RandomAdapter => Random}

object ElementFactory {
  def get(random: Random, withRests: Boolean, values: Seq[Int],
      pitches: Seq[String]): Element = {
    
    if (!withRests) {
      getNote(random, values, pitches)
    } else {
      if (random.nextBoolean) {
        getNote(random, values, pitches)
      } else {
        getRest(random, values)
      }
    }
  }
  
  private def getNote(random: Random, values: Seq[Int],
      pitches: Seq[String]): Note = {
    val value = values(random.nextInt(values.length))
    val pitch = pitches(random.nextInt(pitches.length))
    val element = Note(value, pitch)
    element
  }
  private def getRest(random: Random, values: Seq[Int]): Rest = {
    val value = values(random.nextInt(values.length))
    val element = Rest(value)
    element
  }
}