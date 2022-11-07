package pashayan.charlie.cst.commandline.appoptions

import scala.annotation.tailrec

trait AppOption extends AppOptionable {

  def numArgs: ArgQuantifier
}

sealed trait ArgQuantifier {
  def split(args: Seq[String]): (Seq[String], Seq[String])
}

case object Zero extends ArgQuantifier {
  override def split(args: Seq[String]): (Seq[String], Seq[String]) = (Nil, args)
}

case class Count(value: Int) extends ArgQuantifier {
  assert(value > 0)

  override def split(args: Seq[String]): (Seq[String], Seq[String]) = {
    assert(value <= args.size)
    (args.take(value), args.drop(value))
  }
}

case class TakeWhile(predicate: String => Boolean) extends ArgQuantifier {
  def split(args: Seq[String]): (Seq[String], Seq[String]) = {
    @tailrec
    def loop(remaining: Seq[String], matched: Seq[String]): (Seq[String], Seq[String]) =
      remaining match {
        case head :: tail if predicate(head) => loop(tail, matched :+ head)
        case _ => (matched, remaining)
      }

    loop(args, Nil)
  }
}

object TakeTillNext extends TakeWhile(predicate = s => !s.startsWith("-"))

object AppOption {

  def apply(arg: String): Option[AppOption] =
    AppOptionable.maybeArgLengthAndName(arg).flatMap {
      case (nameLength, name) => All.find(_.matches(nameLength, name))
    }

  val All: Seq[AppOption] = Seq(TransliterateMode, OrderMode, DemoMode, HelpMode, SetInput, SetOutput, SetIncludeConjuncts)
}
