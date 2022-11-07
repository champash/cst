package pashayan.charlie.cst.graphemes

import scala.annotation.tailrec

trait AlphabeticParser extends IndicParser {

  override def read(input: Seq[Char]): Seq[CharPart] = {
    val keys = inMap.map(_._1).toList.sortWith(_.length > _.length)

    @tailrec
    def loop(remaining: Seq[Char], accumulator: Seq[CharPart]): Seq[CharPart] = {
      if (remaining.isEmpty) accumulator
      else keys.find(key => remaining.startsWith(key)) match {
        case Some(key) => loop(remaining.drop(key.length), accumulator :+ inMap(key))
        case None => loop(remaining.drop(1), accumulator :+ Unk)
      }
    }

    loop(input, Nil)
  }

  override def write(input: Seq[CharPart]): Seq[Char] = {
    val keys = outMap.map(_._1).toList

    @tailrec
    def loop(remaining: Seq[CharPart], accumulator: Seq[Char]): Seq[Char] = {
      if (remaining.isEmpty) accumulator
      else {
        val key = keys.find(key => remaining.head == key).getOrElse(Unk)
        loop(remaining.drop(1), accumulator :++ outMap(key))
      }
    }

    loop(input, Nil)
  }


}
