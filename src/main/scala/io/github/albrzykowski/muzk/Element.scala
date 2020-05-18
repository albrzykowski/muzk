package io.github.albrzykowski.muzk

abstract class Element

case class Note(value: Int, var pitch: String) extends Element

case class Rest(value: Int) extends Element