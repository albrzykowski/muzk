package io.github.albrzykowski.muzk.sequence

import io.github.albrzykowski.muzk.{RandomAdapter => Random}

import scala.collection.mutable.ListBuffer
import scala.annotation.tailrec

class RandomWalk(random: Random) {
  
  def generate[A](seq: List[A], length: Int): List[A] = {
    @tailrec
    def iter(random: Random, seq: List[A], length: Int, acc: List[A]): List[A] = {
      if (acc.length == length) {
        return acc
      } else {
        return iter(random, seq, length, seq(random.nextInt(seq.length)) :: acc)
      }
    }
    iter(random, seq, length, List())
  }
}
