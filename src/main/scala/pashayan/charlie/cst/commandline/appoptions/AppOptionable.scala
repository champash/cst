package pashayan.charlie.cst.commandline.appoptions

trait AppOptionable {

  import AppOptionable._

  def shortName: String

  def fullName: String

  def matches(nameLength: NameLength, name: String): Boolean = nameLength match {
    case ShortName => shortName == name
    case FullName => fullName == name
  }
}

object AppOptionable {

  trait NameLength

  case object ShortName extends NameLength

  case object FullName extends NameLength

  def maybeArgLengthAndName(arg: String): Option[(NameLength, String)] =
    if (arg.startsWith("--")) Some((FullName, arg.drop(2)))
    else if (arg.startsWith("-")) Some((ShortName, arg.drop(1)))
    else None

}


