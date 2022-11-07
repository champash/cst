package pashayan.charlie.cst.grammatical

sealed trait PartOfSpeech

case object Adjective extends PartOfSpeech
case object Noun extends PartOfSpeech
case object Verb extends PartOfSpeech

object PartOfSpeech {
  val All: Seq[PartOfSpeech] = Seq(Adjective, Noun, Verb)
}