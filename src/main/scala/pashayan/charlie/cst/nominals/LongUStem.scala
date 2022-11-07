package pashayan.charlie.cst.nominals

import pashayan.charlie.cst.grammatical._

object LongUStem extends Decliner {
  override def trim(stem: String): String =
    if (stem.endsWith("U")) stem.dropRight(1)
    else stem

  val endings: Map[(Number, Case), Seq[String]] = Map(
    (Singular, Nominative) -> Seq("UH"),
    (Singular, Vocative) -> Seq("u"),
    (Singular, Accusative) -> Seq("Um"),
    (Singular, Instrumental) -> Seq("vA"),
    (Singular, Dative) -> Seq("vai"),
    (Singular, Ablative) -> Seq("vAH"),
    (Singular, Genitive) -> Seq("vAH"),
    (Singular, Locative) -> Seq("vAm"),

    (Dual, Nominative) -> Seq("vau"),
    (Dual, Vocative) -> Seq("vau"),
    (Dual, Accusative) -> Seq("vau"),
    (Dual, Instrumental) -> Seq("UbhyAm"),
    (Dual, Dative) -> Seq("UbhyAm"),
    (Dual, Ablative) -> Seq("UbhyAm"),
    (Dual, Genitive) -> Seq("voH"),
    (Dual, Locative) -> Seq("voH"),

    (Plural, Nominative) -> Seq("vaH"),
    (Plural, Vocative) -> Seq("vaH"),
    (Plural, Accusative) -> Seq("UH"),
    (Plural, Instrumental) -> Seq("UbhiH"),
    (Plural, Dative) -> Seq("UbhyaH"),
    (Plural, Ablative) -> Seq("UbhyaH"),
    (Plural, Genitive) -> Seq("UnAm"),
    (Plural, Locative) -> Seq("Usu")
  )

  override def getEnding(number: Number, caze: Case): Seq[String] = endings((number, caze))

}