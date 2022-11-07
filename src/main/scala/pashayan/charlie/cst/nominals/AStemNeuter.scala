
package pashayan.charlie.cst.nominals

import pashayan.charlie.cst.grammatical._

object AStemNeuter extends Decliner {

  override def trim(stem: String): String =
    if (stem.endsWith("a")) stem.dropRight(1)
    else stem

  val endings: Map[(Number, Case), Seq[String]] = Map(
    (Singular, Nominative) -> Seq("am"),
    (Singular, Vocative) -> Seq("a"),
    (Singular, Accusative) -> Seq("am"),
    (Singular, Instrumental) -> Seq("ena"),
    (Singular, Dative) -> Seq("Aya"),
    (Singular, Ablative) -> Seq("At"),
    (Singular, Genitive) -> Seq("asya"),
    (Singular, Locative) -> Seq("e"),

    (Dual, Nominative) -> Seq("e"),
    (Dual, Vocative) -> Seq("e"),
    (Dual, Accusative) -> Seq("e"),
    (Dual, Instrumental) -> Seq("AbhyAm"),
    (Dual, Dative) -> Seq("AbhyAm"),
    (Dual, Ablative) -> Seq("AbhyAm"),
    (Dual, Genitive) -> Seq("ayoH"),
    (Dual, Locative) -> Seq("ayoH"),

    (Plural, Nominative) -> Seq("Ani"),
    (Plural, Vocative) -> Seq("Ani"),
    (Plural, Accusative) -> Seq("Ani"),
    (Plural, Instrumental) -> Seq("aiH"),
    (Plural, Dative) -> Seq("ebhyaH"),
    (Plural, Ablative) -> Seq("ebhyaH"),
    (Plural, Genitive) -> Seq("AnAm"),
    (Plural, Locative) -> Seq("esu")
  )

  override def getEnding(number: Number, caze: Case): Seq[String] = endings((number, caze))
}


