package pashayan.charlie.cst.commandline

import pashayan.charlie.cst.graphemes.IndicTransducer

import scala.annotation.tailrec

import scala.io.StdIn.readLine

class TransliterationRepl(indicTransducer: IndicTransducer) {

  @tailrec
  final def run(): Unit = {
    Option(readLine) match {
      case None =>
      case Some(line) =>
        println(indicTransducer.transduce(line))
        run()
    }
  }
}

object TransliterationRepl {

  def apply(indicTransducer: IndicTransducer): TransliterationRepl = new TransliterationRepl(indicTransducer)
}
