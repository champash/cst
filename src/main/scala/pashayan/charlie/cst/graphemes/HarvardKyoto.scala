package pashayan.charlie.cst.graphemes

import pashayan.charlie.cst.commandline.appoptions.AppOptionable

object HarvardKyoto extends AlphabeticParser with AppOptionable {

  override def fullName: String = "harvard-kyoto"

  override def shortName: String = "hk"

  val charParts: Map[Seq[Char], CharPart] = Map(
    "a" -> A, "A" -> Aa, "i" -> I, "I" -> Ii, "u" -> U, "U" -> Uu,
    "R" -> VowelR, "RR" -> VowelRr, "IR" -> VowelL, "IRR" -> VowelLl,
    "e" -> E, "ai" -> Ai, "o" -> O, "au" -> Au,
    "k" -> K, "kh" -> Kh, "g" -> G, "gh" -> Gh, "G" -> VelarN,
    "c" -> C, "ch" -> Ch, "j" -> J, "jh" -> Jh, "J" -> PalatalN,
    "T" -> RetroflexT, "Th" -> RetroflexTh, "D" -> RetroflexD, "Dh" -> RetroflexDh, "N" -> RetroflexN,
    "t" -> DentalT, "th" -> DentalTh, "d" -> DentalD, "dh" -> DentalDh, "n" -> DentalN,
    "p" -> P, "ph" -> Ph, "b" -> B, "bh" -> Bh, "m" -> M,
    "y" -> Y, "r" -> R, "l" -> L, "v" -> V,
    "z" -> PalatalS, "S" -> RetroflexS, "s" -> S, "h" -> H,
    "M" -> Anusvara, "H" -> Visarga,
    "'" -> Avagraha,
    "auM" -> Aum).map {
    case (string, seq) => (string.toCharArray.toSeq, seq)
  }

  val numerals: Map[Seq[Char], CharPart] = Map(
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
