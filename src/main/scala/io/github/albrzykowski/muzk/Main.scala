package io.github.albrzykowski.muzk

import io.github.albrzykowski.muzk.{RandomAdapter => Random}
import io.github.albrzykowski.muzk.sequence.RandomWalk
import collection.JavaConverters._
import java.io._
import scala.annotation.tailrec

object Main extends App {
  
    val random = Random
    
    val randomWalk = new RandomWalk(random)
    val seq = randomWalk.generate(List("A", "B", "C"), 20)
    
    println(seq)
}
