package pashayan.charlie.cst.nominals

import pashayan.charlie.cst.grammatical.{Dumpable, _}
import pashayan.charlie.cst.flashcards.{AmbiDatum, AmbiDeckable}
import pashayan.charlie.cst.graphemes._
import pashayan.charlie.cst.irregularities.Irregularities

case class NounKey(gender: Gender, number: Number, caze: Case) extends FormKey

case class Noun(stem: String, gender: Gender, irregularities: Irregularities) extends Declinable with Dumpable[NounKey] with AmbiDeckable[NounKey, String] {

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
        val isRoot = trimmed.count(_.isVowel) == 1
        (isRoot, ending, gender) match {
          case (_, Seq(A), Masculine) => Some(AStemMasculine)
          case (_, Seq(A), Neuter) => Some(AStemNeuter)
          case (false, Seq(Ii), _) => Some(LongIStem)
          case (true, Seq(Ii), _) => Some(LongIRoot)
          case (false, Seq(Uu), _) => Some(LongUStem)
          case (true, Seq(Uu), _) => Some(LongURoot)
        }
      }

  override def ambiData: Seq[(AmbiDatum[NounKey], AmbiDatum[String])] = dump.toSeq.flatMap {
    case (nounKey, forms) => forms.map(form => nounKey -> form)
  }.map {
    case (nounKey, form) =>
      AmbiDatum(s"${nounKey.caze.abbr}${nounKey.number.abbr}", nounKey) -> AmbiDatum(form, form)
  }

  override def orderings: (Ordering[NounKey], Ordering[String]) = {
    object nounKeyOrdering extends Ordering[NounKey] {
      override def compare(x: NounKey, y: NounKey): Int = x.number.index compare y.number.index match {
        case zero if zero == 0 => x.caze.index compare y.caze.index
        case nonZero => nonZero
      }
    }
    object stringOrdering extends Ordering[String] {
      override def compare(x: String, y: String): Int = x compare y
    }
    (nounKeyOrdering, stringOrdering)
  }
}
