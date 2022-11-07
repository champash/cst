package pashayan.charlie.cst.commandline.appoptions

case object TransliterateMode extends GameMode {
  override def shortName: String = "t"

  override def fullName: String = "transliterate"

  override def numArgs: ArgQuantifier = Zero
}
