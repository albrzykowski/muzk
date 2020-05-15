package io.github.albrzykowski.muzk

import io.github.albrzykowski.muzk.{RandomAdapter => Random}

class RandomWalk {
  def randomStep(r: Random): Int = {
    if (r.nextBoolean) 1 else -1
  }

  def walk[A](current: A, seq: Seq[A], r: Random): A = {
    if (current == seq.head) {
      seq.take(2).last
    } else if (current == seq.last) {
      seq.takeRight(2).head
    } else {
      seq(seq.indexOf(current) + randomStep(r))
    }
  }
}
object RandomWalk extends RandomWalk {}
