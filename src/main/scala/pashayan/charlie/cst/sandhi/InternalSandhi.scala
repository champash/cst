package pashayan.charlie.cst.sandhi

import scala.annotation.tailrec

class InternalSandhi(rules: Seq[SandhiRule]) {
  def applyTo(string: String): String = {
    @tailrec
    def loop(curString: String, remainingRules: Seq[SandhiRule]): String = remainingRules match {
      case Nil => curString
      case rule :: rest =>
        loop(rule.applyTo(curString), rest)
    }

    StripGlueRule.applyTo(loop(string, rules))
  }
}

object InternalSandhi {
  def apply(rules: Seq[SandhiRule]): InternalSandhi = new InternalSandhi(rules)
}

object AllInternal extends InternalSandhi(Seq(RetroflectNRule, RukiRule))
