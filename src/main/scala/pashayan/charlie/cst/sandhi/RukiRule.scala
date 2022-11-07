package pashayan.charlie.cst.sandhi

object RukiRule extends SandhiRule {
  override def preStr: String =
    ".*" +
      "(?:r|k|" +
      "i|I|u|U|R|RR|IR|IRR|e|ai|o|au" +
      ")" +
      "[MH]*"

  override def subStr: String = "s"

  override def postStr: String = "(?:(?!r).)+"

  override def transformation: String => String = _ => "S"

  override def name: String = "ruki"
}
