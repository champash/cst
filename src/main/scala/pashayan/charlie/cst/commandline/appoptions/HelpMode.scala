package pashayan.charlie.cst.commandline.appoptions

case object HelpMode extends GameMode {
  override def shortName: String = "h"

  override def fullName: String = "help"

  override def numArgs: ArgQuantifier = Zero
}

