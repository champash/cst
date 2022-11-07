
package pashayan.charlie.cst.nominals

import pashayan.charlie.cst.grammatical._

object AaStemFeminine extends Decliner {

  override def trim(stem: String): String =
    if (stem.endsWith("A")) stem.dropRight(1)
    else stem

  val endings: Map[(Number, Case), Seq[String]] = Map(
    (Singular, Nominative) -> Seq("A"),
    (Singular, Vocative) -> Seq("e"),
    (Singular, Accusative) -> Seq("Am"),
    (Singular, Instrumental) -> Seq("ayA"),
    (Singular, Dative) -> Seq("Ayai"),
    (Singular, Ablative) -> Seq("AyAh"),
    (Singular, Genitive) -> Seq("AyAh"),
    (Singular, Locative) -> Seq("AyAm"),

    (Dual, Nominative) -> Seq("e"),
    (Dual, Vocative) -> Seq("e"),
    (Dual, Accusative) -> Seq("e"),
    (Dual, Instrumental) -> Seq("AbhyAm"),
    (Dual, Dative) -> Seq("AbhyAm"),
    (Dual, Ablative) -> Seq("AbhyAm"),
    (Dual, Genitive) -> Seq("ayoH"),
    (Dual, Locative) -> Seq("ayoH"),

    (Plural, Nominative) -> Seq("AH"),
    (Plural, Vocative) -> Seq("AH"),
    (Plural, Accusative) -> Seq("AH"),
    (Plural, Instrumental) -> Seq("AbhiH"),
    (Plural, Dative) -> Seq("AbhAHyaH"),
    (Plural, Ablative) -> Seq("AbhAHyaH"),
    (Plural, Genitive) -> Seq("AnAm"),
    (Plural, Locative) -> Seq("Asu")
  )

  override def getEnding(number: Number, caze: Case): Seq[String] = endings((number, caze))
}
