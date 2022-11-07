package pashayan.charlie.cst.grammatical

sealed trait Case extends GrammaticalTrait {
  override def grammaticalTraitKey: GrammaticalTraitKey = CaseKey
  override def index: Int = Case.All.indexOf(this)
}

case object CaseKey extends GrammaticalTraitKey {
  def name: String = "Case"
}

case object Nominative extends Case {
  override def abbr: String = "Nom"
}

case object Vocative extends Case {
  override def abbr: String = "Voc"
}

case object Accusative extends Case {
  override def abbr: String = "Acc"
}

case object Instrumental extends Case {
  override def abbr: String = "Instr"
}

case object Dative extends Case {
  override def abbr: String = "Dat"
}

case object Ablative extends Case {
  override def abbr: String = "Abl"
}

case object Genitive extends Case {
  override def abbr: String = "Gen"
}

case object Locative extends Case {
  override def abbr: String = "Loc"
}

object Case {
  val All: Seq[Case] = Seq(Nominative, Vocative, Accusative, Instrumental, Dative, Ablative, Genitive, Locative)
  val MaxAbbrWidth: Int = All.map(_.abbr.length).max
}
