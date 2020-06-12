package io.github.albrzykowski.muzk

class RandomAdapter {
  val r = scala.util.Random
  def nextBoolean(): Boolean = {
    r.nextBoolean
  }
  def nextInt(n: Int): Int = {
    r.nextInt(n)
  }
  def shuffle[A](list: List[A]): List[A] = {
    r.shuffle(list)
  }
}
object RandomAdapter extends RandomAdapter {}
