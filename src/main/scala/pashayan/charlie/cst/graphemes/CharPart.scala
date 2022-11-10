package pashayan.charlie.cst.graphemes

import scala.annotation.tailrec

trait CharPart {

  def isStop: Boolean =
    Set(
      K, Kh, G, Gh, VelarN,
      C, Ch, J, Jh, PalatalN,
      RetroflexT, RetroflexTh, RetroflexD, RetroflexDh, RetroflexN,
      DentalT, DentalTh, DentalD, DentalDh, DentalN,
      P, Ph, B, Bh, M
    ).contains(this)

  def isDental: Boolean =
    Set(
      DentalT, DentalTh, DentalD, DentalDh, DentalN
    ).contains(this)

  def isPalatal: Boolean =
    Set(
      C, Ch, J, Jh, PalatalN
    ).contains(this)

  def isRetroflex: Boolean =
    Set(
      RetroflexT, RetroflexTh, RetroflexD, RetroflexDh, RetroflexN
    ).contains(this)

  def isSemivowel: Boolean = (
    Set(
      Y, R, L, V
    ): Set[CharPart]).contains(this)

  def isSibilant: Boolean =
    Set(
      PalatalS, RetroflexS, S, H
    ).contains(this)

  def isConsonant: Boolean = isStop || isSemivowel || isSibilant

  def isVowel: Boolean =
    Set(A, Aa, I, Ii, U, Uu, VowelR, VowelRr, VowelL, VowelLl, E, Ai, O, Au, Aum
    ).contains(this)

  def containsA: Boolean = {
    Set(A, O, E).contains(this)
  }

  def containsAa: Boolean = {
    Set(Aa, Au, Ai).contains(this)
  }

  override def toString: String = this.getClass.getSimpleName.split("\\$").last

}

object CharPart {
  def Letters: Seq[CharPart] =
    Seq(
      A, Aa, I, Ii, U, Uu, VowelR, VowelRr, VowelL, VowelLl, E, Ai, O, Au,
      K, Kh, G, Gh, VelarN,
      C, Ch, J, Jh, PalatalN,
      RetroflexT, RetroflexTh, RetroflexD, RetroflexDh, RetroflexN,
      DentalT, DentalTh, DentalD, DentalDh, DentalN,
      P, Ph, B, Bh, M,
      Y, R, L, V,
      PalatalS, RetroflexS, S, H,
      Anusvara, Visarga
    )

  def Digits: Seq[CharPart] = Seq(
    Zero, One, Two, Three, Four,
    Five, Six, Seven, Eight, Nine
  )

  def All: Seq[CharPart] =
    Seq(Danda, DoubleDanda, Space, Newline, Aum) ++ Digits ++ Letters

  def Index: Map[CharPart, Int] = All.zipWithIndex.toMap

  val OrderOfCount: Int = All.length.toString.length

  @tailrec
  def isOrdered(s1: Seq[CharPart], s2: Seq[CharPart]): Boolean = {
    if (s1.isEmpty) true
    else if (s2.isEmpty) false
    else {
      val i1 = All.indexOf(s1.head)
      val i2 = All.indexOf(s2.head)
      if (i1 < i2) true
      else if (i2 < i1) false
      else isOrdered(s1.tail, s2.tail)
    }
  }

}

object Danda extends CharPart

object DoubleDanda extends CharPart

object Space extends CharPart

object Newline extends CharPart

object Aum extends CharPart

object A extends CharPart

object Aa extends CharPart

object I extends CharPart

object Ii extends CharPart

object U extends CharPart

object Uu extends CharPart

object VowelR extends CharPart

object VowelRr extends CharPart

object VowelL extends CharPart

object VowelLl extends CharPart

object E extends CharPart

object Ai extends CharPart

object O extends CharPart

object Au extends CharPart

object K extends CharPart

object Kh extends CharPart

object G extends CharPart

object Gh extends CharPart

object VelarN extends CharPart

object C extends CharPart

object Ch extends CharPart

object J extends CharPart

object Jh extends CharPart

object PalatalN extends CharPart

object RetroflexT extends CharPart

object RetroflexTh extends CharPart

object RetroflexD extends CharPart

object RetroflexDh extends CharPart

object RetroflexN extends CharPart

object DentalT extends CharPart

object DentalTh extends CharPart

object DentalD extends CharPart

object DentalDh extends CharPart

object DentalN extends CharPart

object P extends CharPart

object Ph extends CharPart

object B extends CharPart

object Bh extends CharPart

object M extends CharPart

object Y extends CharPart

object R extends CharPart

object L extends CharPart

object V extends CharPart

object PalatalS extends CharPart

object RetroflexS extends CharPart

object S extends CharPart

object H extends CharPart

object Anusvara extends CharPart

object Visarga extends CharPart

object Avagraha extends CharPart

object Zero extends CharPart

object One extends CharPart

object Two extends CharPart

object Three extends CharPart

object Four extends CharPart

object Five extends CharPart

object Six extends CharPart

object Seven extends CharPart

object Eight extends CharPart

object Nine extends CharPart

// Attaches morphemes together
object MGlue extends CharPart {
  def value = "#"
}

object Unk extends CharPart