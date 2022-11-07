package pashayan.charlie.cst.flashcards

trait AmbiDeckable[X, Y] {
  def ambiData: Seq[(AmbiDatum[X], AmbiDatum[Y])]
  def orderings: (Ordering[X], Ordering[Y])
}
