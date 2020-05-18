package io.github.albrzykowski.muzk

import io.github.albrzykowski.muzk.{RandomAdapter => Random}

class RandomWalk(random: Random) {
  def randomStep(): Int = {
    if (random.nextBoolean) 1 else -1
  }

  def walk[A](current: A, seq: Seq[A]): A = {
    if (current == seq.head) {
      seq.take(2).last
    } else if (current == seq.last) {
      seq.takeRight(2).head
    } else {
      seq(seq.indexOf(current) + randomStep())
    }
  }

  def generate(
      values: Seq[Int],
      pitches: Seq[String],
      pieceLength: Int,
      withRests: Boolean
  ): List[String] = {
    val element = Element
    if (withRests) {
      element.elementType = ElementType(random.nextInt(ElementType.maxId))
    } else {
      element.elementType = ElementType.Note
    }
    element.value = values(random.nextInt(values.length))
    element.pitch = pitches(random.nextInt(pitches.length))
    val piece = scala.collection.mutable.ListBuffer[String]()

    for (i <- 1 to pieceLength) {
      if (withRests) {
        element.elementType = ElementType(random.nextInt(ElementType.maxId))
      } else {
        element.elementType = ElementType.Note
      }
      element.value = walk[Int](element.value, values)
      if (element.elementType == ElementType.Note) {
        piece += generateNote(element, pitches)
      } else if (element.elementType == ElementType.Rest && withRests) {
        piece += generateRest(element, values)
      }
    }
    piece.toList
  }

  def generateNote(element: Element.type, pitches: Seq[String]): String = {
    element.pitch = walk[String](element.pitch, pitches)
    if (element.pitch.endsWith("#")) {
      val pitch = element.pitch.slice(0, 1)
      s"Send, ${element.value}${pitch}{Up}{Right}"
    } else {
      s"Send, ${element.value}${element.pitch}{Right}"
    }
  }

  def generateRest(element: Element.type, values: Seq[Int]): String = {
    s"Send, ${element.value}0{Right}"
  }
}
