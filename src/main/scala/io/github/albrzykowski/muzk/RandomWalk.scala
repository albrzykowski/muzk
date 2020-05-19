package io.github.albrzykowski.muzk

import io.github.albrzykowski.muzk.{RandomAdapter => Random}

import io.github.albrzykowski.muzk.ElementFactory

class RandomWalk(random: Random) {
  private def randomStep(): Int = {
    if (random.nextBoolean) 1 else -1
  }

  private def walk[A](current: A, seq: Seq[A]): A = {
    if (current == seq.head) {
      seq.take(2).last
    } else if (current == seq.last) {
      seq.takeRight(2).head
    } else {
      seq(seq.indexOf(current) + randomStep())
    }
  }

  def generate(values: Seq[Int], pitches: Seq[String], pieceLength: Int, withRests: Boolean): List[Element] = {
    val element = ElementFactory.get(random, false, values, pitches, None)
    val piece = scala.collection.mutable.ListBuffer[Element]()
    piece += element

    for (i <- 1 to pieceLength - 1) {
      piece += createElement(findLastNote(piece), values, pitches, withRests)
    }

    piece.toList
  }

  private def findLastNote(piece: scala.collection.mutable.ListBuffer[Element]): Note = {
    piece.filter(x => x.isInstanceOf[Note]).last.asInstanceOf[Note]
  }

  private def createElement(current: Element, values: Seq[Int], pitches: Seq[String], withRests: Boolean): Element = {
    val element = ElementFactory.get(random, withRests, values, pitches, None)

    element match {
      case Note(value, pitch) => {
        val currentPitch = walk[String](current.asInstanceOf[Note].pitch, pitches)
        ElementFactory.get(random, withRests, values, pitches, Option(currentPitch))
      }
      case rest: Rest => rest
    }
  }
}
