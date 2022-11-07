package pashayan.charlie.cst.nominals

import pashayan.charlie.cst.grammatical._

object LongURoot extends Decliner {
  override def trim(stem: String): String =
    if (stem.endsWith("U")) stem.dropRight(1)
    else stem

  val endings: Map[(Number, Case), Seq[String]] = Map(
    (Singular, Nominative) -> Seq("UH"),
    (Singular, Vocative) -> Seq("UH"),
    (Singular, Accusative) -> Seq("uvam"),
    (Singular, Instrumental) -> Seq("uvA"),
    (Singular, Dative) -> Seq("uve", "uvai"),
    (Singular, Ablative) -> Seq("uvaH", "uvAH"),
    (Singular, Genitive) -> Seq("uvaH", "uvAH"),
    (Singular, Locative) -> Seq("uvi", "uvAm"),

    (Dual, Nominative) -> Seq("uvau"),
    (Dual, Vocative) -> Seq("uvau"),
    (Dual, Accusative) -> Seq("uvau"),
    (Dual, Instrumental) -> Seq("UbhyAm"),
    (Dual, Dative) -> Seq("UbhyAm"),
    (Dual, Ablative) -> Seq("UbhyAm"),
    (Dual, Genitive) -> Seq("uvoH"),
    (Dual, Locative) -> Seq("uvoH"),

    (Plural, Nominative) -> Seq("aH"),
    (Plural, Vocative) -> Seq("aH"),
    (Plural, Accusative) -> Seq("aH"),
    (Plural, Instrumental) -> Seq("UbhiH"),
    (Plural, Dative) -> Seq("UbhyaH"),
    (Plural, Ablative) -> Seq("UbhyaH"),
    (Plural, Genitive) -> Seq("uvAm", "UnAm"),
    (Plural, Locative) -> Seq("Usu")
  )

  override def getEnding(number: Number, caze: Case): Seq[String] = endings((number, caze))

}