package pashayan.charlie.cst.sandhi
import scala.util.matching.Regex

object SandhiPlayground extends App {

  // simplified N retroflexion rule
  val snrr = new Regex("""(.*(?:r|ṛ|ṝ|ṣ)(?:(?!th)(?!dh).)*)(n)((?:(?!d).+))""", "pre", "sub", "post")

  val t: String => String = {
    case "s" => "ṣ"
    case "n" => "ṇ"
    case s => s
  }

  def applyTo(rule: Regex, word: String): String =
    rule.findFirstMatchIn(word) match {
      case Some(m) =>
        s"""${m.group("pre")}${t(m.group("sub"))}${m.group("post")}"""
      case None => word
    }

  Seq("ramon", "ramone", "rathbone", "renate", "izwarānām", "radhana", "nṛpānām", "randy", "arth#ena").foreach { w =>
    println(s"$w => ${applyTo(snrr, w)}")
  }
}
