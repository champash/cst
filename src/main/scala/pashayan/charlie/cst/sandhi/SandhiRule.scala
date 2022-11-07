package pashayan.charlie.cst.sandhi

import scala.annotation.tailrec
import scala.util.matching.Regex

trait SandhiRule {
  // N.B.: All groups within these strings must be non-capturing.
  // TODO: automatically modify regexes so that any existing groups are non-capturing.
  def preStr: String

  def subStr: String

  def postStr: String

  def transformation: String => String

  def name: String

  def regex: Regex = new Regex(s"($preStr)($subStr)($postStr)", "pre", "sub", "post")

  @tailrec
  final def applyTo(string: String): String = regex.findFirstMatchIn(string) match {
    case None => string
    case Some(matched) =>
      val Seq(pre, sub, post) = Seq(matched.group("pre"), matched.group("sub"), matched.group("post"))
      val sandhied = s"$pre${transformation(sub)}$post"
      applyTo(sandhied)
  }

}