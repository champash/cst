package pashayan.charlie.cst.sandhi

import org.scalatest.{Matchers, WordSpec}

class StripGlueSpec extends WordSpec with Matchers {
  "StripGlueRule" should {
    "strip all glue" in {
      StripGlueRule.applyTo("ava#tar#ati") shouldBe "avatarati"
    }
  }

}
