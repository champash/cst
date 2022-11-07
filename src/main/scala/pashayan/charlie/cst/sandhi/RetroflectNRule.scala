package pashayan.charlie.cst.sandhi

import pashayan.charlie.cst.graphemes.MGlue

object RetroflectNRule extends SandhiRule {

  private val NoTongueAction: String =
    "(?:" +
      "(?!t)(?!th)(?!d)(?!dh)(?!n)" + // dental
      "(?!T)(?!Th)(?!D)(?!Dh)(?!N)" + // retroflex
      "(?!c)(?!ch)(?!j)(?!jh)(?!J)" + // palatal
      ".)*"

  override def preStr: String =
    ".*" +
      "(?:S|r|R|RR)" +
      NoTongueAction +
      MGlue.value +
      NoTongueAction

  override def subStr: String = "n"

  override def postStr: String =
    "(?:(?!d).)+"

  override def transformation: String => String = _ => "N"

  override def name: String = "retroflect N"
}