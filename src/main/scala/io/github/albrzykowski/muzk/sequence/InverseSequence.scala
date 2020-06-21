package io.github.albrzykowski.muzk.sequence

class InverseSequence() {

  def findNoteIndex(note: String): Int = {
    val base = List("C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B")

    base.indexOf(note)
  }

  def pivote(notes: List[String]): List[String] = {
    val base = List("C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B")
    val lists = base.splitAt(base.indexOf(notes.head))
    lists._2 ++ lists._1
  }

  def generate(notes: List[String]): List[String] = {
    val pivotalNote = notes.head
    val pivotedNotes = pivote(notes)
    val indices = notes.map(n => { (12 - (-1 * (findNoteIndex(pivotalNote) - findNoteIndex(n)))) % 12 })
    indices.map(i => pivotedNotes(i))
  }
}
