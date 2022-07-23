package lectures.part1

object DarkSugar extends App {
  val aThread = new Thread(new Runnable {
    override def run(): Unit = println("thread")
  })

  val betterThread = new Thread(() => println("better one"))

  abstract class AnAbstractOne {
    def someMethod: Int = 233
    def functionM(a: Int): Unit
  }

  val abstractInstanceWithFunctionM: AnAbstractOne = (b: Int) => println("a")

  //last char decides associativity of method
  val strange = 1 :: 2 :: 3 :: List(4, 5)
  val soTheSame = List(4,5).::(3).::(2).::(1)
  println(strange)
  println(soTheSame)

  class MyStream[T] {
    def -->:(value: T): MyStream[T] = this // actual implementation here
  }

  val myStream = 1 -->: 2 -->: 3 -->: new MyStream[Int]

  class TeenGirl(name: String) {
    def `and then said`(gossip: String) = println(s"$name said $gossip")
  }

  val lilly = new TeenGirl("Lilly")
  lilly `and then said` "Scala is so sweet!"

  // syntax sugar #6: update() is very special, much like apply()
  val anArray = Array(1,2,3)
  anArray(2) = 7  // rewritten to anArray.update(2, 7)
  // used in mutable collections
  // remember apply() AND update()!

  println(anArray.mkString("Array(", ", ", ")"))

  // syntax sugar #7: setters for mutable containers
  class Mutable {
    private var internalMember: Int = 0 // private for OO encapsulation
    def memberT = internalMember // "getter"
    def memberT_=(value: Int): Unit =
      internalMember = value // "setter"
  }

  val aMutableContainer = new Mutable
  aMutableContainer.memberT = 42 // rewrittern as aMutableContainer.member_=(42)
  println(aMutableContainer.memberT)

  // syntax sugar #5: infix types
  class Composite[A, B]
  val composite: Int Composite String = ???

  class -->[A, B]
  val towards: Int --> String = ???
}
