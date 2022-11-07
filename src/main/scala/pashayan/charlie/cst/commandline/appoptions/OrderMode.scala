package pashayan.charlie.cst.commandline.appoptions

case object OrderMode extends GameMode {
  override def numArgs: ArgQuantifier = Zero

  override def shortName: String = "ot"

  override def fullName: String = "order-trainer"
}
