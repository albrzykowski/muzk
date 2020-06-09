package io.github.albrzykowski.muzk

import io.github.albrzykowski.muzk.{RandomAdapter => Random}
import io.github.albrzykowski.muzk.sequence.RandomWalk
import collection.JavaConverters._
import java.io._
import scala.annotation.tailrec

object Main extends App {

  val random = Random

  val randomWalk = new RandomWalk(random)
  val seq = randomWalk.generate(List("C", "D", "E", "F", "G", "A", "B"), 10)
  val seq2 = randomWalk.generate(List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), 10)

  println(seq)
  println(seq2)
}
