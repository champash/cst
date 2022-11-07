package pashayan.charlie.cst.flashcards

import scala.annotation.tailrec
import scala.util.Random
import AmbiDeck._

case class AmbiDeck[X, Y](sides: (Seq[(AmbiDatum[X], Seq[AmbiDatum[Y]])], Seq[(AmbiDatum[Y], Seq[AmbiDatum[X]])]), orderings: (Ordering[X], Ordering[Y]), thisSideUp: Boolean) {
  def flip: AmbiDeck[Y, X] = AmbiDeck((sides._2, sides._1), (orderings._2, orderings._1), !thisSideUp)

  def slip(gotItRight: Boolean): AmbiDeck[X, Y] = sides match {
    case (head :: tail, bottom) =>
      val breakPoint =
        if (gotItRight) tail.length
        else tail.length / 3 + Random.nextInt(tail.length / 3 + 1)
      val (front, back) = tail.splitAt(breakPoint)
      AmbiDeck(
        (front ++ (head :: back),
          bottom),
        orderings,
        thisSideUp
      )
  }

  def pop: Option[(String, Seq[String])] =
    sides._1.headOption.map {
      case (ambiDatum, ambiData) =>
        (ambiDatum.value, ambiData.map(_.value))
    }

    def merge(ambiDeck: AmbiDeck[X, Y]): AmbiDeck[X, Y] = {

      require(orderings == ambiDeck.orderings)

      AmbiDeck((
        buildDeckSide(sides._1 ++ ambiDeck.sides._1, orderings),
        buildDeckSide(sides._2 ++ ambiDeck.sides._2, (orderings._2, orderings._1))),
        orderings,
        thisSideUp = true)
    }

    def shuffle: AmbiDeck[X, Y] = AmbiDeck(
      (Random.shuffle(sides._1),
        Random.shuffle(sides._2)),
      orderings,
      thisSideUp
    )

  def sort: AmbiDeck[X, Y] = AmbiDeck(
    (sides._1.sortBy(_._1.sortingValue)(orderings._1),
      sides._2.sortBy(_._1.sortingValue)(orderings._2)),
    orderings,
    thisSideUp
  )
}

object AmbiDeck {
  // type DeckSide[X, Y] = Seq[(AmbiDatum[X], Seq[AmbiDatum[Y]])]

  @tailrec
  def buildDeckSide[X, Y](remaining: Seq[(AmbiDatum[X], Seq[AmbiDatum[Y]])],
                          orderings: (Ordering[X], Ordering[Y]),
                          map: Map[AmbiDatum[X], Set[AmbiDatum[Y]]] = Map(): Map[AmbiDatum[X], Set[AmbiDatum[Y]]]
                         ): Seq[(AmbiDatum[X], Seq[AmbiDatum[Y]])] = remaining match {
    case Nil => map.keys.toSeq.sortBy(_.sortingValue)(orderings._1).map(key => key -> map(key).toSeq.sortBy(_.sortingValue)(orderings._2))
    case (ambiDatum, ambiData) :: tail =>
      buildDeckSide(tail, orderings, map + (ambiDatum -> (map.getOrElse(ambiDatum, Set()) ++ ambiData)))
  }

  def apply[X, Y](ambiData: Seq[(AmbiDatum[X], AmbiDatum[Y])], orderings: (Ordering[X], Ordering[Y])): AmbiDeck[X, Y] = {
    val deckSideTop = ambiData.map {
      case (ambiDatum1, ambiDatum2) => ambiDatum1 -> Seq(ambiDatum2)
    }
    val deckSideBottom = ambiData.map {
      case (ambiDatum1, ambiDatum2) => ambiDatum2 -> Seq(ambiDatum1)
    }
    //AmbiDeck((buildDeckSide(deckSideTop, orderings), buildDeckSide(deckSideBottom, (orderings._2, orderings._1))), orderings, thisSideUp = true)
    ???
  }
}