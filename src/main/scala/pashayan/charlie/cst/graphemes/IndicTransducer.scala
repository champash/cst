package pashayan.charlie.cst.graphemes

class IndicTransducer(from: IndicParser, onto: IndicParser) {
  def transduce(input: String): String = {
    onto.write(from.read(input)).mkString
  }
}

object IndicTransducer {
  def apply(from: IndicParser, onto: IndicParser): IndicTransducer = new IndicTransducer(from, onto)
}