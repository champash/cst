package pashayan.charlie.cst.flashcards

// This is just a case class to make equality checks easier.
case class OrderNode(maybeNodeType: Option[NodeType] = None, maybeStringValue: Option[String] = None, maybeIntValue: Option[Int] = None)

sealed trait NodeType
case object StringType extends NodeType
case object IntType extends NodeType

object OrderNode {
  def apply(string: String): OrderNode = new OrderNode(Some(StringType), Some(string), None)

  def apply(int: Int): OrderNode = new OrderNode(Some(IntType), None, Some(int))

  object OrderNodeOrdering extends Ordering[OrderNode] {
    def compare(x: OrderNode, y: OrderNode): Int = (x.maybeNodeType, y.maybeNodeType) match {
      case (Some(diff1), Some(diff2)) if diff1 != diff2 => 0
      case (Some(StringType), _) =>
        (for {
          xVal <- x.maybeStringValue
          yVal <- y.maybeStringValue
        } yield xVal compare yVal).getOrElse(0)
      case (Some(IntType), _) =>
        (for {
          xVal <- x.maybeIntValue
          yVal <- y.maybeIntValue
        } yield xVal compare yVal).getOrElse(0)
    }
  }
}


