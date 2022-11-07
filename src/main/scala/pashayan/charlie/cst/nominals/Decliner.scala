package pashayan.charlie.cst.nominals

import pashayan.charlie.cst.grammatical._
import pashayan.charlie.cst.graphemes._
import pashayan.charlie.cst.sandhi.AllInternal

trait Decliner {
  def getEnding(number: Number, caze: Case): Seq[String]

  def trim(stem: String): String

  def apply(stem: String, number: Number, caze: Case): Seq[String] = {
    val prepped = trim(stem) + MGlue.value
    val endings = getEnding(number, caze)
    val ended = endings.map(prepped + _)
    val sandhied = ended.map(AllInternal.applyTo)
    sandhied
  }
}