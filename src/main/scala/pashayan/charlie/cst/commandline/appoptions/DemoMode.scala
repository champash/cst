package pashayan.charlie.cst.commandline.appoptions


case object DemoMode extends GameMode {
  override def numArgs: ArgQuantifier = Zero

  override def shortName: String = "d"

  override def fullName: String = "demo"
}
