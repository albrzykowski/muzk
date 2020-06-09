package io.github.albrzykowski.muzk.composer

import io.github.albrzykowski.muzk.sequence.{RandomWalk => RandomWalkSequence}
import io.github.albrzykowski.muzk.{RandomAdapter => Random}
import scala.collection.mutable.ListBuffer

object RandomWalk {
  
  val random = Random
  val randomWalk = new RandomWalkSequence(random)
  
  def compose(pitches: List[String], values: List[String], types: List[String], length: Int): List[(String, String, String)] = {
    var result: List[(String, String, String)] = List()
    
    val randomPitches = randomWalk.generate(pitches, length)
    val randomValues = randomWalk.generate(values, length)
    val randomTypes = randomWalk.generate(types, length)
    
    for( i <- 0 to length-1) {
      result = (randomPitches(i), randomValues(i), randomTypes(i)) :: result
    }
    
    result.toList
  }
}