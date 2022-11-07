package pashayan.charlie.cst.verbs

import pashayan.charlie.cst.irregularities.Irregularity

case class Verb(root: String,
                clazz: Int,
                preverbs: Seq[Preverb] = Nil,
                irregularities: List[Irregularity]) {

}