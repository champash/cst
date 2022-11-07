package pashayan.charlie.cst.irregularities

import pashayan.charlie.cst.nominals.Decliner

case class IrregularDecliner(decliner: Decliner) extends Irregularity {
  override def irregularityKey: IrregularityKey = IrregularDeclinerKey
}

case object IrregularDeclinerKey extends IrregularityKey