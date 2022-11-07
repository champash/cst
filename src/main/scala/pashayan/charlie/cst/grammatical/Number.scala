package pashayan.charlie.cst.grammatical

sealed trait Number extends GrammaticalTrait {
  case object NumberKey extends GrammaticalTraitKey {
    override def name: String = "Number"
  }

  override def grammaticalTraitKey: GrammaticalTraitKey = NumberKey
  override def index: Int = Number.All.indexOf(this)
}

case object Singular extends Number {
  override def abbr: String = "Sg"
}

case object Dual extends Number {
  override def abbr: String = "Du"
}

case object Plural extends Number {
  override def abbr: String = "Pl"
}

object Number {
  val All: Seq[Number] = Seq(Singular, Dual, Plural)
  val MaxAbbrWidth: Int = All.map(_.abbr.length).max

}