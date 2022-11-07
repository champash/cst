package pashayan.charlie.cst.grammatical

sealed trait Gender extends GrammaticalTrait {
  case object GenderKey extends GrammaticalTraitKey {
    override def name: String = "Gender"
  }
  override def grammaticalTraitKey: GrammaticalTraitKey = GenderKey
  override def index: Int = Gender.All.indexOf(this)
}

case object Masculine extends Gender {
  override def abbr: String = "Masc"
}

case object Neuter extends Gender {
  override def abbr: String = "Neut"
}

case object Feminine extends Gender {
  override def abbr: String = "Fem"
}

object Gender {
  val All: Seq[Gender] = Seq(Masculine, Neuter, Feminine)
  val MaxAbbrWidth: Int = All.map(_.abbr.length).max
}