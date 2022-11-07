package pashayan.charlie.cst.sandhi

object StripGlueRule extends SandhiRule {
  override def preStr: String = ".*"

  override def subStr: String = "#"

  override def postStr: String = ".*"

  override def transformation: String => String = _ => ""

  override def name: String = "string glue"
}
