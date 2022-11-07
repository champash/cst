package pashayan.charlie.cst.commandline.appoptions

case object SetInput extends AppOption {
  override def shortName: String = "i"

  override def fullName: String = "input"

  override def numArgs: ArgQuantifier = Count(1)
}
