package pashayan.charlie.cst.grammatical

trait Dumpable[T <: FormKey] {

  def dump: Map[T, Seq[String]]
}
