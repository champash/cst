package pashayan.charlie.cst.sandhi

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AsyncWordSpec

class StripGlueSpec extends AsyncWordSpec with Matchers {
  "StripGlueRule" should {
    "strip all glue" in {
      StripGlueRule.applyTo("ava#tar#ati") shouldBe "avatarati"
    }
  }

}
