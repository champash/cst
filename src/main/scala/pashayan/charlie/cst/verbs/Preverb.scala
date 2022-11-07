package pashayan.charlie.cst.verbs

sealed trait Preverb {
  def string: String
}

case object Apa extends Preverb {
  override def string: String = "apa"
}

case object Api extends Preverb {
  override def string: String = "api"
}

case object Pra extends Preverb {
  override def string: String = "pra"
}

case object Vi extends Preverb {
  override def string: String = "vi"
}