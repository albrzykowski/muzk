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
      pieceLength: Int
  ): List[String] = {
    val element = Element
    element.elementType = ElementType(random.nextInt(ElementType.maxId))
    element.value = values(random.nextInt(values.length))
    element.pitch = pitches(random.nextInt(pitches.length))
    val piece = scala.collection.mutable.ListBuffer[String]()

    for (i <- 1 to pieceLength) {
      element.elementType = ElementType(random.nextInt(ElementType.maxId))
      element.value = walk[Int](element.value, values)

      if (element.elementType == ElementType.Note) {
        element.pitch = walk[String](element.pitch, pitches)
      }

      if (element.elementType == ElementType.Note) {
        if (element.pitch.endsWith("#")) {
          element.pitch = element.pitch.slice(0, 1)
          piece += s"Send, ${element.value}${element.pitch}{Up}{Right}"
        } else {
          piece += s"Send, ${element.value}${element.pitch}{Right}"
        }
      } else {
        piece += s"Send, ${element.value}0{Right}"
      }
    }
    piece.toList
  }
}
