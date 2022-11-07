package pashayan.charlie.cst.graphemes

import scala.annotation.tailrec

trait BrahmicParser extends IndicParser {

  def virama: Char

  def inherentVowel: CharPart

  def independentFormMap: Map[CharPart, Seq[Char]]

  override def read(input: Seq[Char]): Seq[CharPart] = {
    val keys = inMap.map(_._1).toList

    @tailrec
    def loop(remaining: Seq[Char], acc: Seq[CharPart], prevWasConsonant: Boolean): Seq[CharPart] = {
      if (remaining.isEmpty) acc
      else if (remaining(0) == virama) {
        loop(remaining.drop(1), acc, prevWasConsonant = true)
      }
      else keys.find(key => remaining.startsWith(key)) match {
        case Some(key) =>
          val charPart = inMap(key)
          if (prevWasConsonant && !charPart.isVowel)
            loop(remaining, acc :+ inherentVowel, prevWasConsonant = false)
          else loop(remaining.drop(key.length), acc :+ charPart, prevWasConsonant = charPart.isConsonant)
        case None => loop(remaining.drop(1), acc :+ Unk, prevWasConsonant = false)
      }
    }

    loop(input, Nil, prevWasConsonant = false)
  }

  override def write(input: Seq[CharPart]): Seq[Char] = {
    val keys = outMap.map(_._1).toList

    @tailrec
    def loop(remaining: Seq[CharPart], acc: Seq[Char], prevWasConsonant: Boolean): Seq[Char] = {
      if (remaining.isEmpty) {
        val maybeVirama =
          if (prevWasConsonant) Seq(virama)
          else Nil
        acc :++ maybeVirama
      }
      else {
        val key = keys.find(key => remaining.head == key).getOrElse(Unk)
        val maybeVirama =
          if (prevWasConsonant && !key.isVowel) Seq(virama)
          else Nil
        val cur =
          if (key.isVowel && !prevWasConsonant) independentFormMap(key)
          else outMap(key)
        loop(remaining.drop(1), acc :++ maybeVirama :++ cur, key.isConsonant)
      }
    }

    loop(input, Nil, prevWasConsonant = false)
  }

}
