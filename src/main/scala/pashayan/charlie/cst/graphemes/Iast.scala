package pashayan.charlie.cst.graphemes

import pashayan.charlie.cst.commandline.appoptions.AppOptionable

object Iast extends AlphabeticParser with AppOptionable {

  override def fullName: String = "iast"

  override def shortName: String = "i"

  val charParts: Map[Seq[Char], CharPart] = Map(
    "a" -> A, "ā" -> Aa, "i" -> I, "ī" -> Ii, "u" -> U, "ū" -> Uu,
    "ṛ" -> VowelR, "ṝ" -> VowelRr, "ḷ" -> VowelL, "ḹ" -> VowelLl,
    "e" -> E, "ai" -> Ai, "o" -> O, "au" -> Au,
    "k" -> K, "kh" -> Kh, "g" -> G, "gh" -> Gh, "ṅ" -> VelarN,
    "c" -> C, "ch" -> Ch, "j" -> J, "jh" -> Jh, "ñ" -> PalatalN,
    "ṭ" -> RetroflexT, "ṭh" -> RetroflexTh, "ḍ" -> RetroflexD, "ḍh" -> RetroflexDh, "ṇ" -> RetroflexN,
    "t" -> DentalT, "th" -> DentalTh, "d" -> DentalD, "dh" -> DentalDh, "n" -> DentalN,
    "p" -> P, "ph" -> Ph, "b" -> B, "bh" -> Bh, "m" -> M,
    "y" -> Y, "r" -> R, "l" -> L, "v" -> V,
    "ś" -> PalatalS, "ṣ" -> RetroflexS, "s" -> S, "h" -> H,
    "ṃ" -> Anusvara, "ḥ" -> Visarga,
    "'" -> Avagraha,
    "auṃ" -> Aum).map {
    case (string, seq) => (string.toCharArray.toSeq, seq)
  }

  val numerals: Map[Seq[Char], CharPart]= Map(
    "0" -> Zero, "1" -> One, "2" -> Two, "3" -> Three, "4" -> Four,
    "5" -> Five, "6" -> Six, "7" -> Seven, "8" -> Eight, "9" -> Nine
  ).map {
    case (string, seq) => (string.toCharArray.toSeq, seq)
  }

  val whitespace: Map[Seq[Char], CharPart] = Map(
    " " -> Space
  ).map {
    case (string, seq) => (string.toCharArray.toSeq, seq)
  }

  val punctuation: Map[Seq[Char], CharPart] = Map(
    "|" -> Danda, "||" -> DoubleDanda
  ).map {
    case (string, seq) => (string.toCharArray.toSeq, seq)
  }

  override def inMap: Map[Seq[Char], CharPart] = charParts ++ numerals ++ whitespace ++ punctuation ++ unknownCharacterMap
}
