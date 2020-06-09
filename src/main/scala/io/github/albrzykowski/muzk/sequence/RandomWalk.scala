package io.github.albrzykowski.muzk.sequence

import io.github.albrzykowski.muzk.{RandomAdapter => Random}

import scala.collection.mutable.ListBuffer
import scala.annotation.tailrec

class RandomWalk(random: Random) {
  private def randomStep(): Int = {
    if (random.nextBoolean) 1 else -1
  }

  private def nextStep[A](current: A, seq: List[A]): A = {
    if (current == seq.head) {
      seq.take(2).last
    } else if (current == seq.last) {
      seq.takeRight(2).head
    } else {
      seq(seq.indexOf(current) + randomStep())
    }
  }

  def generate[A](seq: List[A], length: Int): List[A] = {
    @tailrec
    def iter(random: Random, seq: List[A], length: Int, acc: List[A]): List[A] = {
      if (acc.length == length) {
        return acc
      } else {
        return iter(random, seq, length, nextStep(acc.head, seq) :: acc)
      }
    }
    iter(random, seq, length, List(seq(random.nextInt(seq.length))))
  }
}
