package pashayan.charlie.cst.commandline

import pashayan.charlie.cst.commandline.appoptions.{AppOption, DemoMode, GameMode, HelpMode, OrderMode, SetIncludeConjuncts, SetInput, SetOutput, TransliterateMode}
import pashayan.charlie.cst.graphemes._

import scala.annotation.tailrec
import scala.util.Random

object Cst {

  private case class BuildingOptions(maybeGameMode: Option[GameMode],
                                     maybeInputParser: Option[IndicParser],
                                     maybeOutputParser: Option[IndicParser],
                                     maybeIncludeConjuncts: Option[Boolean])

  private object DefaultOptions extends BuildingOptions(Some(TransliterateMode), Some(HarvardKyoto), Some(Devanagari), Some(true))

  private case class Options(gameMode: GameMode, inputParser: IndicParser, outputParser: IndicParser, includeConjuncts: Boolean)

  private def buildOptions(args: Array[String]): Option[Options] = {
    @tailrec
    def loop(remaining: Seq[String], buildingOptions: BuildingOptions): Option[Options] = {
      remaining match {
        case Nil =>
          for {
            gameMode <- buildingOptions.maybeGameMode
            input <- buildingOptions.maybeInputParser
            output <- buildingOptions.maybeOutputParser
            includeConjuncts <- buildingOptions.maybeIncludeConjuncts
          } yield Options(gameMode, input, output, includeConjuncts)
        case head :: tail =>
          AppOption(head) match {
            case Some(TransliterateMode) =>
              loop(tail, buildingOptions.copy(maybeGameMode = Some(TransliterateMode)))
            case Some(OrderMode) =>
              loop(tail, buildingOptions.copy(maybeGameMode = Some(OrderMode)))
            case Some(DemoMode) =>
              loop(tail, buildingOptions.copy(maybeGameMode = Some(OrderMode)))
            case Some(HelpMode) =>
              loop(tail, buildingOptions.copy(maybeGameMode = Some(HelpMode)))
            case Some(SetInput) =>
              val (optArgs, newTail) = SetInput.numArgs.split(tail)
              val maybeParser = IndicParser(optArgs.head)
              loop(newTail, buildingOptions.copy(maybeInputParser = maybeParser))
            case Some(SetOutput) =>
              val (optArgs, newTail) = SetOutput.numArgs.split(tail)
              val maybeParser = IndicParser(optArgs.head)
              loop(newTail, buildingOptions.copy(maybeOutputParser = maybeParser))
            case Some(SetIncludeConjuncts) =>
              val (optArgs, newTail) = SetIncludeConjuncts.numArgs.split(tail)
              val includeConjuncts: Boolean = optArgs.head.toLowerCase == "true"
              loop(newTail, buildingOptions.copy(maybeIncludeConjuncts = Some(includeConjuncts)))
            case None =>
              println("Unknown option: $head")
              None
            case _ =>
              println("Unimplemented option: $head")
              None
          }
      }
    }
    loop(args.toList, DefaultOptions)
  }

  def main(args: Array[String]): Unit = {
    for {
      options <- buildOptions(Array())
    } yield {
      import options._
      implicit val random: Random = new Random
      gameMode match {
        case TransliterateMode =>
          val indicTransducer = IndicTransducer(inputParser, outputParser)
          val transliterationRepl = TransliterationRepl(indicTransducer)
          transliterationRepl.run()
        case OrderMode =>
          val orderRepl = OrderRepl(includeConjuncts, outputParser)
          orderRepl.run()
        case DemoMode =>
          Demo.run()
        case HelpMode =>
          println("Help is not implemented yet. Check the README.")
      }
    }
  }
}
