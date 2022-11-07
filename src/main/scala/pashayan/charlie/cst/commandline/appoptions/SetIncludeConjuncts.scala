package pashayan.charlie.cst.commandline.appoptions

case object SetIncludeConjuncts extends AppOption {
  override def shortName: String = "ic"

  override def fullName: String = "include-conjuncts"

  override def numArgs: ArgQuantifier = Count(1)
}
