package pashayan.charlie.cst.commandline

import pashayan.charlie.cst.grammatical._
import pashayan.charlie.cst.graphemes._
import pashayan.charlie.cst.nominals._

object Demo {
  def run(): Unit = main(Array.empty)

  def dumpNominal(word: String, decliner: Decliner, indicParser: IndicParser): Unit = {
    def process(strings: Seq[String]): String = strings.map(s => CharvardKyoto.read(s.toCharArray)).map(Iast.write(_).mkString).mkString(", ")

    def padTo(string: String, padChar: Char, width: Int, before: Boolean): String = {
      val padCount =
        if (string.length < width) width - string.length
        else 0
      val padding = padChar.toString * padCount
      if (before) padding + string
      else string + padding
    }

    val wordMatrix: Seq[(String, String, String)] = Case.All.map { caze =>
      val singular = process(decliner(word, Singular, caze))
      val dual = process(decliner(word, Dual, caze))
      val plural = process(decliner(word, Plural, caze))
      (singular, dual, plural)
    }
    val sHeader = "singular"
    val dHeader = "dual"
    val pHeader = "plural"
    val padding = 2
    val rowWidth = wordMatrix.foldRight((sHeader.length, dHeader.length + 2, pHeader.length + 2)) { (cur, acc) =>
      (Seq(acc._1, cur._1.length + padding).max, Seq(acc._2, cur._2.length + padding).max, Seq(acc._3, cur._3.length).max)
    }
    val sHeaderPadded = padTo(sHeader, padChar = ' ', width = rowWidth._1, before = false)
    val dHeaderPadded = padTo(dHeader, padChar = ' ', rowWidth._2, before = false)
    val pHeaderPadded = padTo(pHeader, padChar = ' ', rowWidth._3, before = false)
    val headerPadding = " " * (Case.MaxAbbrWidth + 2)
    println(s"$headerPadding$sHeaderPadded$dHeaderPadded$pHeaderPadded")
    println((" " * (Case.MaxAbbrWidth + 1)) + "|" + ("-" * (rowWidth._1 + rowWidth._2 + rowWidth._3)))
    Case.All.zip(wordMatrix).foreach {
      case (caze, (singular, dual, plural)) =>
        val s = padTo(singular, padChar = ' ', rowWidth._1, before = false)
        val d = padTo(dual, padChar = ' ', rowWidth._2, before = false)
        val p = padTo(plural, padChar = ' ', rowWidth._3, before = false)
        val c = padTo(caze.abbr, padChar = ' ', width = 5, before = true)
        println(s"$c |$s$d$p")
    }
  }

  def main(args: Array[String]): Unit = {
    println("Welcome to CST Demo Mode")
    // Transliteration
    val sampleSentence = "yadihAsti tadanyatra yannehAsti na tatkvacit"

    val charParts = HarvardKyoto.read(sampleSentence)
    val harvardKyoto = HarvardKyoto.write(charParts)
    val iast = Iast.write(charParts)
    val devanagari = Devanagari.write(charParts)

    println("CST can transliterate into and out of ...")
    println(s"Harvard-Kyoto: ${harvardKyoto.mkString}")
    println(s"IAST: ${iast.mkString}")
    println(s"Devanagari: ${devanagari.mkString}")

    // TODO: write tests for decliners based on table on 393.
    println
    // Declension
    println("CST can decline ...")
    println("masculine a-stem:")
    dumpNominal("tathAgatha", AStemMasculine, Iast)
    println

    println("noun with n-retroflexion:")
    dumpNominal("izvara", AStemMasculine, Iast)
    println

    println("neuter a stem:")
    dumpNominal("vana", AStemNeuter, Iast)
    println

    println("neuter a stem:")
    dumpNominal("mitra", AStemNeuter, Iast)
    println

    println("long a stem:")
    dumpNominal("bAlA", AaStemFeminine, Iast)
    println

    println("long i stem")
    dumpNominal("nadI", LongIStem, Iast)
    println

    println("long i root")
    dumpNominal("dhI", LongIRoot, Iast)
    println

    println("long u stem")
    dumpNominal("vadhU", LongUStem, Iast)
    println

    println("long u root")
    dumpNominal("bhU", LongURoot, Iast)
    println
  }

}
