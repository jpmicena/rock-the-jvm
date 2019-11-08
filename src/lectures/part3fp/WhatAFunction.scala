package lectures.part3fp

object WhatAFunction extends App {

  // DREAM: use functions as first class elements
  // problem: oop

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  println(doubler(2))

  // function types = Function1, Function2... up to Function22
  val stringToIntConverter = new Function1[String, Int] {
    override def apply(string: String): Int = string.toInt
  }

  println(stringToIntConverter("3") +  4)

  // all scala functions are objects

  /*
    1. a function which takes 2 strings and concatenates them
    2. transform the MyPredicate and MyTransformer into function types
    3. define a function which takes an int and returns another function which takes and int and returns an int
      - what's the type of this function
      - how to do it
   */

  val concatenator: ((String, String) => String) = {
    (a, b) => a + b
  }

  println(concatenator("hello", "world"))

//  val superAdder: Function1[Int, Function1[Int, Int]] = new Function1[Int, Function1[Int, Int]] {
//    override def apply(x: Int): Function1[Int, Int] = new Function1[Int, Int] {
//      override def apply(y: Int): Int = x + y
//    }
//  }

  val superAdder: Int => Int => Int = (x: Int) => (y: Int) => x + y

  val adder3 = superAdder(3)
  println(adder3(4))
  println(superAdder(3)(4)) // curried function

}

trait MyFunction[A, B] {
  def apply(element: A): B
}
