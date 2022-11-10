package pashayan.charlie.cst.flashcards

import scala.annotation.tailrec
import scala.util.Random
import AmbiDeck._
import pashayan.charlie.cst.flashcards.CustomOrder.CustomOrderOrdering

case class AmbiDeck(sides: (Seq[(AmbiDatum, Seq[AmbiDatum])], Seq[(AmbiDatum, Seq[AmbiDatum])]), thisSideUp: Boolean) {
  def flip: AmbiDeck = AmbiDeck((sides._2, sides._1), !thisSideUp)

  def slip(gotItRight: Boolean): AmbiDeck = sides match {
    case (head :: tail, bottom) =>
      val breakPoint =
        if (gotItRight) tail.length
        else tail.length / 3 + Random.nextInt(tail.length / 3 + 1)
      val (front, back) = tail.splitAt(breakPoint)
      AmbiDeck(
        (front ++ (head :: back),
          bottom),
        thisSideUp
      )
  }

  def top: Option[(String, Seq[String])] =
    sides._1.headOption.map {
      case (ambiDatum, ambiData) =>
        (ambiDatum.value, ambiData.map(_.value))
    }

  def merge(ambiDeck: AmbiDeck): AmbiDeck = {

    AmbiDeck((
      buildDeckSide(sides._1 ++ ambiDeck.sides._1),
      buildDeckSide(sides._2 ++ ambiDeck.sides._2)),
      thisSideUp = true)
  }

  def shuffle: AmbiDeck = AmbiDeck(
    (Random.shuffle(sides._1),
      Random.shuffle(sides._2)),
    thisSideUp
  )

  def sort: AmbiDeck = AmbiDeck(
    (sides._1.sortBy(_._1.customOrder)(CustomOrderOrdering),
      sides._2.sortBy(_._1.customOrder)(CustomOrderOrdering)),
    thisSideUp
  )
}

object AmbiDeck {
  @tailrec
  def buildDeckSide(remaining: Seq[(AmbiDatum, Seq[AmbiDatum])],
                    map: Map[AmbiDatum, Set[AmbiDatum]] = Map(): Map[AmbiDatum, Set[AmbiDatum]]
                   ): Seq[(AmbiDatum, Seq[AmbiDatum])] = remaining match {
    case Nil => map.keys.toSeq.sortBy(_.customOrder)(CustomOrderOrdering).map(key => key -> map(key).toSeq.sortBy(_.customOrder)(CustomOrderOrdering))
    case (ambiDatum, ambiData) :: tail =>
      buildDeckSide(tail, map + (ambiDatum -> (map.getOrElse(ambiDatum, Set()) ++ ambiData)))
  }

  def apply(ambiData: Seq[(AmbiDatum, AmbiDatum)]): AmbiDeck = {
    val deckSideTop = ambiData.map {
      case (ambiDatum1, ambiDatum2) => ambiDatum1 -> Seq(ambiDatum2)
    }
    val deckSideBottom = ambiData.map {
      case (ambiDatum1, ambiDatum2) => ambiDatum2 -> Seq(ambiDatum1)
    }
    AmbiDeck((buildDeckSide(deckSideTop), buildDeckSide(deckSideBottom)), thisSideUp = true)
  }
}