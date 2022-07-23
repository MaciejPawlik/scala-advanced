package lectures.part1

import lectures.part1.AdvancedPatterMatching.MyList
import lectures.part1.Recap.MyList

object AdvancedPatterMatching extends App {

  val numbers = List(1)

  val description = numbers match {
    case head :: Nil => println(s"Only one: $head") // Nil is empty list
    case _ =>
  }

  class Person(val name: String, val age: Int) //this is not case class but we can apply pattern matching

  object PersonPattern { //single
    def unapply(person: Person): Option[(String, Int)] = Some((person.name, person.age)) // unapply for pattern matching

    def unapply(age: Int): Option[String] = Some(if (age >= 18) "adult" else "youngster")
  }

  val bob = new Person("Bobek", 55)

  val greetings = bob match {
    case PersonPattern(n, a) => s"(ciao, sono $n, ho $a anni)" //PersonPattern to show it has nothing to do with person class
  }

  println(greetings)

  val legalStatus = bob.age match {
    case PersonPattern(status) => s"(my status: $status)"
  }

  println(legalStatus)

  object even {
    def unapply(arg: Int): Boolean = arg % 2 == 0
  }

  object singleDigit {
    def unapply(arg: Int): Boolean = arg > -10 && arg < 10
  }

  val n: Int = 8
  val mathProperty = n match {
    case singleDigit() => "single digit" // instead of keeping ifs here, moreover object can be reused for other pattern matching
    case even() => "an even number"
    case _ => "no property"
  }

  println(mathProperty)

  //infix patterns (works for two)
  case class Or[A, B](a: A, b: B)
  val either = Or(2, "two")
  val humanDescription = either match {
    case Or(number, string) => s"$number written $string"
  }

  //decomposing sequence
  val varargs = numbers match {
    case List(1, _*) => "list starts with 1"
  }

  abstract class MyList[+A] {
    def head: A = ???
    def tail: MyList[A] = ???
  }
  case object Empty extends MyList[Nothing]
  case class Cons[+A](override val head: A, override val tail: MyList[A]) extends MyList[A]

  object MyList {
    def unapplySeq[A](list: MyList[A]): Option[Seq[A]] =
      if (list == Empty) Option(Seq.empty)
      else unapplySeq(list.tail).map(list.head +: _)
  }

  val myList: MyList[Int] = Cons(1, Cons(2, Cons(3, Empty)))
  val decomposed = myList match {
    case MyList(1, 2, _*) => "starts with 1 and 2"
    case _ => "something else"
  }

  //customer returned type for unapply
  //isEmpty, get
  abstract class Wrapper[T] {
    def isEmpty: Boolean
    def get: T
  }

  object PersonWrapper {
    def unapply(person: Person): Wrapper[String] = new Wrapper[String] {
      override def isEmpty: Boolean = false
      override def get: String = person.name
    }
  }

  println(bob match {
    case PersonWrapper(n) => s"Person with name $n"
    case _ => "alien"
  })
}
