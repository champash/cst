package pashayan.charlie.cst.graphemes

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AsyncWordSpec

class ConjunctsSpec extends AsyncWordSpec with Matchers {
  "Conjuncts" should {
    "have the right number of conjuncts" in {
      Conjuncts.All.length shouldBe 211
    }
    "have no repeated values" in {
      Conjuncts.All.toSet.size shouldBe Conjuncts.All.length
    }

    "have no members with length less than 2" in {
      Conjuncts.All.filter(_.length < 2) shouldBe Nil
    }

    "be ordered" in {
      Conjuncts.All.sortWith(CharPart.isOrdered) shouldBe Conjuncts.All
    }
  }
}
