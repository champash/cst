package pashayan.charlie.cst.nominals

import pashayan.charlie.cst.flashcards._
import pashayan.charlie.cst.grammatical.{Dumpable, _}
import pashayan.charlie.cst.graphemes._
import pashayan.charlie.cst.irregularities.Irregularities

case class NounKey(gender: Gender, number: Number, caze: Case) extends FormKey

case class Noun(stem: String, gender: Gender, irregularities: Irregularities) extends Declinable with Dumpable[NounKey] with AmbiDeckable {

  override def dump: Map[NounKey, Seq[String]] = getDecliner match {
    case None => Map()
    case Some(decliner) =>
      (for {
        number <- Number.All
        caze <- Case.All
      } yield {
        NounKey(gender, number, caze) -> decliner(stem, number, caze)
      }).toMap
  }

  override def getDecliner: Option[Decliner] =
    irregularities.irregularDecliner.map(_.decliner)
      .orElse {
        val charvardized = CharvardKyoto.read(stem)
        val (trimmed, ending) = charvardized.splitAt(charvardized.length - 1)
        val isRoot = trimmed.count(_.isVowel) == 0
        (isRoot, ending, gender) match {
          case (_, Seq(A), Masculine) => Some(AStemMasculine)
          case (_, Seq(A), Neuter) => Some(AStemNeuter)
          case (false, Seq(Ii), _) => Some(LongIStem)
          case (true, Seq(Ii), _) => Some(LongIRoot)
          case (false, Seq(Uu), _) => Some(LongUStem)
          case (true, Seq(Uu), _) => Some(LongURoot)
        }
      }

  override def ambiData: Seq[(AmbiDatum, AmbiDatum)] = dump.toSeq.flatMap {
    case (nounKey, forms) => forms.map(form => nounKey -> form)
  }.map {
    case (nounKey, form) =>
      AmbiDatum(form, CustomOrder(CharvardKyotoStringTypeKey, Seq(OrderNode(CharvardKyoto.toComparableString(form))))) ->
        AmbiDatum(s"${nounKey.caze.abbr} ${nounKey.number.abbr}",
          CustomOrder(NounKeyTypeKey, Seq(OrderNode(CharvardKyoto.toComparableString(stem)), OrderNode(nounKey.number.index), OrderNode(nounKey.caze.index))))

  }
}