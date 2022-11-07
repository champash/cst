package pashayan.charlie.cst.playground

object FindOut {

  type mySeq[X] = Seq[MyContainer[X]]

  case class MyContainer[X](value: X)

  case class ContainerContainer[X](seq: mySeq[X], ordering: Ordering[X]) {

    def orderize: ContainerContainer[X] = ContainerContainer(seq.sortBy(_.value)(ordering), ordering)
  }

  object myOrder extends Ordering[Int] {
    def compare(a: Int, b: Int): Int = a compare b
  }


  def main(args: Array[String]): Unit = {
    val myContainers: Seq[MyContainer[Int]] = Seq(MyContainer(23), MyContainer(17), MyContainer(666), MyContainer(420))
    val containerContainer = ContainerContainer(myContainers, myOrder)
    println(containerContainer.seq)
    println(containerContainer.orderize.seq)
  }
}
