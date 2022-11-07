package pashayan.charlie.cst.commandline.appoptions

case object SetOutput extends AppOption {
  override def shortName: String = "o"

  override def fullName: String = "output"

  override def numArgs: ArgQuantifier = Count(1)
}
