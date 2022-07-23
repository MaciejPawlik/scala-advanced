package lectures.part1

import scala.annotation.tailrec

object Recap extends App {
  if (true) 42 else 56

  def myFunction(x: Int): Int = x + 2

  @tailrec def factorial(n: Int, accumulator: Int): Int = if (n <= 0) accumulator else factorial(n - 1, n * accumulator)

  println(factorial(3, 1))

  class Animal
  class Cat extends Animal
  val cat: Animal = new Cat // subtyping polymorphism

  trait Carnivore {
    def eat(animal: Animal): Unit
  }

  class Crocodile extends Animal with Carnivore {
    override def eat(animal: Animal): Unit = println("Mniam!")
  }

  //method notation
  val croc = new Crocodile
  croc.eat(cat)
  croc eat cat

  //anonymous classes
  val crog = new Crocodile {
    override def eat(animal: Animal): Unit = println("Papcis, papcis!")
  }
  crog.eat(cat)

  //generics
  abstract class MyList[+A]
  //singleton and companions
  object MyList

  case class Person(name: String, age: Int)

  //exceptions
  val ex = throw new RuntimeException // type nothing
  val failure = try {
    ex
  } catch {
    case e: Exception => "exception caught!"
  } finally {
    println("everytime!")
  }

  //functional programming
  val increm = new Function[Int, Int] {
    override def apply(v1: Int): Int = v1 + 1
  }
  increm(2)

  val anonymousInc = (x: Int) => x + 1
  List(2, 3, 5).map(anonymousInc) //Higher Order Funct.

  //for-comprehension
  val pairs = for {
    numbers <- List(1, 2, 3)
    chars <- List('a', 'b', 'c')
  } yield numbers +  "-"  + chars
  println(pairs)

  //Options, try
  val option = Some(2)

  //patterns matching
  val x = 2
  val order = x match {
    case 1 => "first"
    case 2 => "second"
    case 3 => "third"
    case _ => x + "th"
  }

  val bob = Person("Bob", 33)

  val greeting = bob match {
    case Person(n, _) => s"Hi, I am $n"
  }
}
