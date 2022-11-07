package pashayan.charlie.cst.nominals

import pashayan.charlie.cst.grammatical._

object LongIRoot extends Decliner {
  override def trim(stem: String): String =
    if (stem.endsWith("I")) stem.dropRight(1)
    else stem

  val endings: Map[(Number, Case), Seq[String]] = Map(
    (Singular, Nominative) -> Seq("IH"),
    (Singular, Vocative) -> Seq("IH"),
    (Singular, Accusative) -> Seq("iyam"),
    (Singular, Instrumental) -> Seq("iyA"),
    (Singular, Dative) -> Seq("iye", "iyai"),
    (Singular, Ablative) -> Seq("iyaH", "iyAH"),
    (Singular, Genitive) -> Seq("iyaH", "iyAH"),
    (Singular, Locative) -> Seq("iyi", "iyAm"),

    (Dual, Nominative) -> Seq("iyau"),
    (Dual, Vocative) -> Seq("iyau"),
    (Dual, Accusative) -> Seq("iyau"),
    (Dual, Instrumental) -> Seq("Ibhyam"),
    (Dual, Dative) -> Seq("Ibhyam"),
    (Dual, Ablative) -> Seq("Ibhyam"),
    (Dual, Genitive) -> Seq("iyoH"),
    (Dual, Locative) -> Seq("iyoH"),

    (Plural, Nominative) -> Seq("iyaH"),
    (Plural, Vocative) -> Seq("iyaH"),
    (Plural, Accusative) -> Seq("iyaH"),
    (Plural, Instrumental) -> Seq("IbhiH"),
    (Plural, Dative) -> Seq("IbhyaH"),
    (Plural, Ablative) -> Seq("IbhyaH"),
    (Plural, Genitive) -> Seq("IyAm", "InAm"),
    (Plural, Locative) -> Seq("Isu")
  )

  override def getEnding(number: Number, caze: Case): Seq[String] = endings((number, caze))

}
