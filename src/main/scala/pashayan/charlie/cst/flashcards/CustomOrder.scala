package pashayan.charlie.cst.flashcards

import scala.annotation.tailrec

case class CustomOrder(dataTypeKey: DataTypeKey, orderNodes: Seq[OrderNode])

object CustomOrder {

  object CustomOrderOrdering extends Ordering[CustomOrder] {

    import DataTypeKey.DataTypeKeyOrdering

    override def compare(x: CustomOrder, y: CustomOrder): Int = {
      @tailrec
      def loop(xs: Seq[OrderNode], ys: Seq[OrderNode]): Int = {
        import OrderNode.OrderNodeOrdering
        if (xs.length != ys.length) 0
        else if (xs.isEmpty && ys.isEmpty) 0
        else {
          val xh :: xt = xs
          val yh :: yt = ys
          val c = OrderNodeOrdering.compare(xh, yh)
          if (c != 0) c
          else loop(xt, yt)
        }
      }

      DataTypeKeyOrdering.compare(x.dataTypeKey, y.dataTypeKey) match {
        case 0 => loop(x.orderNodes, y.orderNodes)
        case n => n
      }
    }
  }

}