package pashayan.charlie.cst.graphemes

import pashayan.charlie.cst.commandline.appoptions.AppOptionable

trait IndicParser extends AppOptionable {

  def unknownCharacterMap: Map[Seq[Char], CharPart] = Map(
    "□" -> Unk
  ).map {
    case (string, seq) => (string.toCharArray.toSeq, seq)
  }

  def inMap: Map[Seq[Char], CharPart]

  def outMap: Map[CharPart, Seq[Char]] = inMap.map {
    case (chars, charParts) => (charParts, chars)
  }

  def read(input: Seq[Char]): Seq[CharPart]

  def write(input: Seq[CharPart]): Seq[Char]

}

object IndicParser {

  def apply(arg: String): Option[IndicParser] =
    AppOptionable.maybeArgLengthAndName(arg).flatMap {
      case (nameLength, name) => All.find(_.matches(nameLength, name))
    }

  val All: Seq[IndicParser] = Seq(HarvardKyoto, Iast, Devanagari, CharvardKyoto)

}
