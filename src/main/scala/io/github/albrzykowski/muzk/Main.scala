package io.github.albrzykowski.muzk

import io.github.albrzykowski.muzk.{RandomAdapter => Random}
import io.github.albrzykowski.muzk.RandomWalk
import com.typesafe.config.ConfigFactory
import collection.JavaConverters._
import java.io._

object Main extends App {

  val conf = ConfigFactory.load();

  object Element {
    var elementType: ElementType.Value = _
    var value: Int = _
    var pitch: String = _
  }

  object ElementType extends Enumeration {
    type ElementType = Value
    val Note, Rest = Value
  }

  val musescoreValues = conf.getIntList("values").asScala.toSeq.map(i => i: Int)
  val musescorePitches =
    conf.getStringList("pitches").asScala.toSeq.map(i => i: String)
  val pieceLength = conf.getInt("piece-length")
  val random = Random
  val randomWalk = RandomWalk

  def generate(values: Seq[Int], pitches: Seq[String], pieceLength: Int): List[String] = {
    val element = Element
    element.elementType = ElementType(random.nextInt(ElementType.maxId))
    element.value = values(random.nextInt(values.length))
    element.pitch = pitches(random.nextInt(pitches.length))
    val piece = scala.collection.mutable.ListBuffer[String]()

    for (i <- 0 to pieceLength) {
      element.elementType = ElementType(random.nextInt(ElementType.maxId))
      element.value = randomWalk.walk[Int](element.value, values, random)

      if (element.elementType == ElementType.Note) {
        element.pitch = randomWalk.walk[String](element.pitch, pitches, random)
      }

      if (element.elementType == ElementType.Note) {
        piece += s"Send, ${element.value}${element.pitch}{Right}"
      } else {
        piece += s"Send, ${element.value}0{Right}"
      }
    }
    piece.toList
  }

  def save(piece: List[String]): Unit = {
    val file = conf.getString("output-file")
    val writer = new BufferedWriter(new FileWriter(file))
    piece.foreach(e => { writer.write(s"${e}\r\n") })
    writer.close()
  }

  val piece = generate(musescoreValues, musescorePitches, pieceLength)
  save(piece)
}
