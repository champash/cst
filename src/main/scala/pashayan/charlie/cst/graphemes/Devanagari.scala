package pashayan.charlie.cst.graphemes

import pashayan.charlie.cst.commandline.appoptions.AppOptionable

object Devanagari extends BrahmicParser with AppOptionable {
  override def virama: Char = '\u094d'

  // Note: The addition of independent vowel forms means this is no longer bijective.
  val charParts: Map[Seq[Char], CharPart] = Map(
    "ा" -> Aa, "ि" -> I, "ी" -> Ii, "ु" -> U, "ू" -> Uu,
    "ृ" -> VowelR, "ॄ" -> VowelRr, "ॢ" -> VowelL, "ॣ" -> VowelLl,
    "े" -> E, "ै" -> Ai, "ो" -> O, "ौ" -> Au,
    "क" -> K, "ख" -> Kh, "ग" -> G, "घ" -> Gh, "ङ" -> VelarN,
    "च" -> C, "छ" -> Ch, "ज" -> J, "झ" -> Jh, "ञ" -> PalatalN,
    "ट" -> RetroflexT, "ठ" -> RetroflexTh, "ड" -> RetroflexD, "ढ" -> RetroflexDh, "ण" -> RetroflexN,
    "त" -> DentalT, "थ" -> DentalTh, "द" -> DentalD, "ध" -> DentalDh, "न" -> DentalN,
    "प" -> P, "फ" -> Ph, "ब" -> B, "भ" -> Bh, "म" -> M,
    "य" -> Y, "र" -> R, "ल" -> L, "व" -> V,
    "श" -> PalatalS, "ष" -> RetroflexS, "स" -> S, "ह" -> H,
    "ं" -> Anusvara, "ः" -> Visarga,
    "ऽ" -> Avagraha,
    "ॐ" -> Aum
  ).map {
    case (string, seq) => (string.toCharArray.toSeq, seq)
  }

  val numerals: Map[Seq[Char], CharPart] = Map(
    "०" -> Zero, "१" -> One, "२" -> Two, "३" -> Three, "४" -> Four,
    "५" -> Five, "६" -> Six, "७" -> Seven, "८" -> Eight, "९" -> Nine
  ).map {
    case (string, seq) => (string.toCharArray.toSeq, seq)
  }

  val whitespace: Map[Seq[Char], CharPart] = Map(
    " " -> Space
  ).map {
    case (string, seq) => (string.toCharArray.toSeq, seq)
  }

  val punctuation: Map[Seq[Char], CharPart] = Map(
    "।" -> Danda, "॥" -> DoubleDanda
  ).map {
    case (string, seq) => (string.toCharArray.toSeq, seq)
  }

  override def inherentVowel: CharPart = A

  val inherentVowelMap: Map[CharPart, Seq[Char]] = Map(inherentVowel -> Seq())

  override def inMap: Map[Seq[Char], CharPart] = charParts ++ numerals ++ whitespace ++ punctuation ++ independentFormMap.map {
    case (charParts, chars) => (chars, charParts)
  } ++ unknownCharacterMap

  override def outMap: Map[CharPart, Seq[Char]] = inherentVowelMap ++
    (charParts ++ numerals ++ whitespace ++ punctuation ++ unknownCharacterMap).map {
      case (charParts, chars) => (chars, charParts)
    }

  override def shortName: String = "d"

  override def fullName: String = "devanagari"

  override def independentFormMap: Map[CharPart, Seq[Char]] = Map(
    A -> "अ", Aa -> "आ", I -> "इ", Ii -> "ई", U -> "उ", Uu -> "ऊ",
    VowelR -> "ऋ", VowelRr -> "ॠ", VowelL -> "ऌ", VowelLl -> "ॡ",
    E -> "ए", Ai -> "ऐ", O -> "ओ", Au -> "औ"
  )
}
