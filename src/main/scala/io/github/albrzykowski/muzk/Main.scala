package io.github.albrzykowski.muzk

import io.github.albrzykowski.muzk.{RandomAdapter => Random}
import io.github.albrzykowski.muzk.RandomWalk
import com.typesafe.config.ConfigFactory
import collection.JavaConverters._
import java.io._

object Main extends App {

  val conf = ConfigFactory.load();

  val musescoreValues = conf.getIntList("values").asScala.toSeq.map(i => i: Int)
  val musescorePitches = conf.getStringList("pitches").asScala.toSeq.map(i => i: String)
  val pieceLength = conf.getInt("piece-length")
  val withRests = conf.getBoolean("with-rests")
  val random = Random
  val randomWalk = new RandomWalk(random)

  def save(piece: List[String]): Unit = {
    val file = conf.getString("output-file")
    val writer = new BufferedWriter(new FileWriter(file))
    piece.foreach(e => { writer.write(s"${e}\r\n") })
    writer.close()
  }

  def toHotkey(element: Element): String = {
    element match {
      case Note(value, pitch) =>
        s"Send, ${value}${pitch}{Right}"
      case Rest(value) =>
        s"Send, ${value}0{Right}"
    }
  }

  val piece = randomWalk.generate(musescoreValues, musescorePitches, pieceLength, withRests)
  val hotkeys = piece.map(toHotkey)
  save(hotkeys)
}
