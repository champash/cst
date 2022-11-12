package pashayan.charlie.cst.sandhi

import org.scalatest.{Matchers, WordSpec}

class RukiRuleSpec extends WordSpec with Matchers {
  "RukiRule" should {
    val triggers = Seq("r", "k", "i", "I", "u", "U", "R", "RR", "IR", "IRR", "e", "ai", "o", "au")
    val nonBlockers = Seq("", "M", "H", "#")
    val blockers = Seq("n", "t", "p")

    "esu should turn to eSu" in {
      RukiRule.applyTo("esu") shouldBe "eSu"
    }

    "retroflect s after any of the triggers, with or without intervening non-blockers" in {
      val words = for {
        trigger <- triggers
        nonBlocker1 <- nonBlockers
        nonBlocker2 <- nonBlockers
      } yield s"$trigger$nonBlocker1${nonBlocker2}su"

      words.map(RukiRule.applyTo).filterNot(_.endsWith("Su")) shouldBe Nil
    }

    "don't retroflect s after any of the triggers, if something other than a non-blocker intervenes" in {
      val triggers = Seq("r", "k", "i", "I", "u", "U", "R", "RR", "IR", "IRR")
      val nonBlockers = Seq("", "M", "H", "#")
      val words = for {
        trigger <- triggers
        nonBlocker1 <- nonBlockers
        nonBlocker2 <- nonBlockers
        blocker <- blockers
      } yield s"$trigger$nonBlocker1$nonBlocker2${blocker}su"

      words.map(RukiRule.applyTo).filterNot(_.endsWith("su")) shouldBe Nil
    }

    "don't retroflect s at the end of the word" in {
      RukiRule.applyTo("dingus") shouldBe "dingus"
    }

    "don't retroflect s preceding r" in {
      RukiRule.applyTo("ksr") shouldBe "ksr"

    }
  }

}
