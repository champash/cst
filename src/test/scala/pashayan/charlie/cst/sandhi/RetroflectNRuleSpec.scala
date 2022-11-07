package pashayan.charlie.cst.sandhi

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AsyncWordSpec

class RetroflectNRuleSpec extends AsyncWordSpec with Matchers {

  "RetroflectNRule" should {
    val blockers = Seq(
      "t", "th", "d", "dh", "n",
      "T", "Th", "D", "Dh", "N",
      "c", "ch", "j", "jh", "J"
    )

    "retroflect n after an s" in {
      RetroflectNRule.applyTo("RS#ina") shouldBe "RS#iNa"
    }

    "retroflect n after an r" in {
      RetroflectNRule.applyTo("izwar#AnAm") shouldBe "izwar#ANAm"
    }

    "retroflect n after an R" in {
      RetroflectNRule.applyTo("izwar#AnAm") shouldBe "izwar#ANAm"
    }

    "retroflect n after an RR" in {
      RetroflectNRule.applyTo("*abatRRv#ena") shouldBe "*abatRRv#eNa"
    }

    "don't retroflect n after an intervening letter of certain classes, when the trigger letter comes first" in {

      val words = blockers.map { blocker =>
        s"ra$blocker#ini"
      }
      words.map(RetroflectNRule.applyTo) shouldBe words
    }

    "don't retroflect n after an intervening letter of certain classes, when the trigger letter comes in the middle of the root" in {
      val words = blockers.map { blocker =>
        s"ara$blocker#ini"
      }
      words.map(RetroflectNRule.applyTo) shouldBe words
    }

    "don't retroflect n after an intervening letter of certain classes, when the blocker comes after the glue" in {
      val words = blockers.map { blocker =>
        s"ar#${blocker}ini"
      }
      words.filterNot(_.endsWith("ini")) shouldBe Nil
    }
  }

}
