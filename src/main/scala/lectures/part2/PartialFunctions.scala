package lectures.part2

object PartialFunctions extends App {
  val aFunction = (x: Int) => x + 1 // Function1[Int, Int] === Int => Int

  val aNiceFunction = (x: Int) => x match {
    case 1 => 42
    case 2 => 56
    case 5 => 33
  }

  val aPartialFunction: PartialFunction[Int, Int] = {
    case 1 => 42
    case 2 => 56
    case 5 => 33
  } // part function value

  println(aPartialFunction(2))
  //println(aPartialFunction(6)) //throws exception

  //PF utilities
  println(aPartialFunction.isDefinedAt(56))

  //lift
  val lifted = aPartialFunction.lift // Int => Option[Int]
  println(lifted(2))
  println(lifted(45))

  val aPFChain = aPartialFunction.orElse[Int, Int] {
    case 22 => 2342
  }
  println(aPFChain(2))
  println(aPFChain(22))

  // partial function extends total function
  val aTotalFunction: Int => Int = {
    case 1 => 99
  }

  // higher order function accepts partial functions
  val aMappedList = List(1, 2, 3).map {
    case 1 => 12
    case 2 => 44
    case 3 => 9
  }
  println(aMappedList)
  // PF can have only one param type

  val aManualPF = new PartialFunction[Int, Int] {
    override def isDefinedAt(x: Int): Boolean = x == 1 || x == 2 || x == 5

    override def apply(x: Int): Int = x match {
      case 1 => 33
      case 2 => 122
      case 5 => 99
    }
  }

  val chatBot: PartialFunction[String, String] = {
    case "hello" => "Hi, my name is dummy bot"
    case "how are you?" => "I'm ok"
  }

  scala.io.Source.stdin.getLines().map(chatBot).foreach(println)
}
