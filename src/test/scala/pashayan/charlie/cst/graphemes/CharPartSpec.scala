package pashayan.charlie.cst.graphemes

import org.scalatest.{Matchers, WordSpec}


class CharPartSpec extends WordSpec with Matchers {

  "CharPart" should {
    "know when two characters are ordered" in {
      val s1 = Seq(E)
      val s2 = Seq(PalatalS)
      CharPart.isOrdered(s1, s2) shouldBe true
    }

    "know when two characters are not ordered" in {
      val s1 = Seq(E)
      val s2 = Seq(PalatalS)
      CharPart.isOrdered(s2, s1) shouldBe false
    }
  }

}
