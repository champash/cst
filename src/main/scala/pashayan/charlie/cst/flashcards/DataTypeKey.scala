package pashayan.charlie.cst.flashcards

trait DataTypeKey

object DataTypeKey {
  val All: Seq[DataTypeKey] = Seq(CharvardKyotoStringTypeKey, NounKeyTypeKey)
  val Index: Map[DataTypeKey, Int] = All.zipWithIndex.toMap

  object DataTypeKeyOrdering extends Ordering[DataTypeKey] {
    override def compare(x: DataTypeKey, y: DataTypeKey): Int =
      (for {
        xx <- Index.get(x)
        yy <- Index.get(y)
      } yield xx compare yy).getOrElse(0)
  }
}

case object CharvardKyotoStringTypeKey extends DataTypeKey

case object NounKeyTypeKey extends DataTypeKey