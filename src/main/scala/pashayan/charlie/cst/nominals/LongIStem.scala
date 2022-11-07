package pashayan.charlie.cst.nominals

import pashayan.charlie.cst.grammatical._

object LongIStem extends Decliner {
  override def trim(stem: String): String =
    if (stem.endsWith("I")) stem.dropRight(1)
    else stem

  val endings: Map[(Number, Case), Seq[String]] = Map(
    (Singular, Nominative) -> Seq("I"),
    (Singular, Vocative) -> Seq("i"),
    (Singular, Accusative) -> Seq("Im"),
    (Singular, Instrumental) -> Seq("yA"),
    (Singular, Dative) -> Seq("yai"),
    (Singular, Ablative) -> Seq("yAH"),
    (Singular, Genitive) -> Seq("yAH"),
    (Singular, Locative) -> Seq("yAm"),

    (Dual, Nominative) -> Seq("yau"),
    (Dual, Vocative) -> Seq("yau"),
    (Dual, Accusative) -> Seq("yau"),
    (Dual, Instrumental) -> Seq("IbhyAm"),
    (Dual, Dative) -> Seq("IbhyAm"),
    (Dual, Ablative) -> Seq("IbhyAm"),
    (Dual, Genitive) -> Seq("yoH"),
    (Dual, Locative) -> Seq("yoH"),

    (Plural, Nominative) -> Seq("yaH"),
    (Plural, Vocative) -> Seq("yaH"),
    (Plural, Accusative) -> Seq("IH"),
    (Plural, Instrumental) -> Seq("IbhiH"),
    (Plural, Dative) -> Seq("IbhyaH"),
    (Plural, Ablative) -> Seq("IbhyaH"),
    (Plural, Genitive) -> Seq("InAm"),
    (Plural, Locative) -> Seq("Isu")
  )

  override def getEnding(number: Number, caze: Case): Seq[String] = endings((number, caze))

}
