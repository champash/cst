package pashayan.charlie.cst.commandline

import pashayan.charlie.cst.graphemes.{A, Anusvara, CharPart, Conjuncts, IndicParser, Visarga}

import scala.annotation.tailrec
import scala.io.StdIn.readLine
import scala.util.Random

class OrderRepl(letters: Seq[Seq[CharPart]], outputParser: IndicParser)(implicit random: Random) {
  @tailrec
  final def run(): Unit = {
    val a1 = 'a'
    val a2 = 'b'
    val Seq(c1, c2) = random.shuffle(letters).take(2)
    val o1 = outputParser.write(c1)
    val o2 = outputParser.write(c2)
    println("Which comes first, a or b?")
    println(s"$a1:\t${o1.mkString}\t$a2:\t${o2.mkString}")
    println
    Option(readLine) match {
      case Some(line) if !line.isEmpty =>
        val answer = line.filter(a => Set(a1, a2).contains(a)).last
        val expected =
          if (CharPart.isOrdered(c1, c2)) a1
          else a2
        if (answer == expected) println("Yes!")
        else println("No.")
        run()
      case _ =>
    }

  }
}

object OrderRepl {
  def apply(includeConjuncts: Boolean, outputParser: IndicParser)(implicit random: Random): OrderRepl = {
    val letters: Seq[Seq[CharPart]] =
      (CharPart.Letters.filterNot(Seq(Visarga, Anusvara).contains).map(Seq(_)) ++ {
        if (includeConjuncts) Conjuncts.All else Seq()
      }).map {
        case seq if seq.last.isConsonant => seq :+ A
        case seq => seq
      }
    new OrderRepl(letters,
      outputParser)
  }
}
