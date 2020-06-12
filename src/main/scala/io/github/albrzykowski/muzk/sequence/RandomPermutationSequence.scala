package io.github.albrzykowski.muzk.sequence

import io.github.albrzykowski.muzk.{RandomAdapter => Random}

class RandomPermutationSequence(random: Random) {

  def generate[A](seq: List[A]): List[A] = {
    random.shuffle(seq)
  }
}
