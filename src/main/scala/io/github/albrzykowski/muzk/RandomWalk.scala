package io.github.albrzykowski.muzk

import io.github.albrzykowski.muzk.{RandomAdapter => Random}

import io.github.albrzykowski.muzk.ElementFactory

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
  ): List[Element] = {
    val element = ElementFactory.get(random, withRests, values, pitches)
    val piece = scala.collection.mutable.ListBuffer[Element]()
    
    for (i <- 1 to pieceLength) {
      val element = ElementFactory.get(random, withRests, values, pitches)
      element match {
        case n: Note => {
          println(walk[String](n.pitch, pitches))
          n.pitch = walk[String](n.pitch, pitches)
        }
        case Rest(value) => false
      }
      piece += element 
    }
    piece.toList
  }
}
