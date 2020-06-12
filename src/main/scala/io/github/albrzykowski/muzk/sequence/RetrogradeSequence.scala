package io.github.albrzykowski.muzk.sequence

class RetrogradeSequence() {

  def generate[A](seq: List[A]): List[A] = {
    seq.reverse
  }
}
