package pashayan.charlie.cst.graphemes

import pashayan.charlie.cst.commandline.appoptions.AppOptionable

object CharvardKyoto extends AlphabeticParser with AppOptionable {
  override def fullName: String = "charvard-kyoto"

  override def shortName: String = "ck"

  val specialLetters: Map[Seq[Char], CharPart] = Map(
    MGlue.value -> MGlue
  ).map {
    case (string, seq) => (string.toCharArray.toSeq, seq)
  }

  override def inMap: Map[Seq[Char], CharPart] = HarvardKyoto.inMap ++ specialLetters
}
