package pashayan.charlie.cst.irregularities

case class Irregularities(values: List[Irregularity]) {
  def irregularDecliner: Option[IrregularDecliner] =
    values.find(_.irregularityKey == IrregularDeclinerKey).map(_.asInstanceOf[IrregularDecliner])

}
