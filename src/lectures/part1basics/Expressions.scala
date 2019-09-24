package lectures.part1basics

object Expressions extends App {

  val x = 1 + 2 // EXPRESSION
  println(x)

  println(2 + 3 * 4)
  // + - * / (basic math)
  // & | ^ << >> >>> [right shift with zero extension] (bitwise operators)

  println(1 == x)
  // == != > >= < <=

  println(!(1 == x))
  // !  && ||

  var aVariable = 2
  aVariable += 3 // also works with -= *= /= these are all side effects
  println(aVariable)

  // Instructions (DO) vs Expressions (VALUE) [In scala we''l think more about expressions]

  // IF Expression
  val aCondition = true
  val aConditionedValue = if (aCondition) 5 else 3 // IF is Expression because it give backs a value
  println(aConditionedValue)
  println(if (aCondition) 5 else 3)

  var i = 0
  while (i < 10) {
    println(i)
    i += 1
  }

  // NEVER WRITE THIS AGAIN (avoid loops)
  // Everything in Scala is an Expression

  val aWeirdValue = (aVariable = 3) // Unit === void
  println(aWeirdValue)

  // side effects: println(), whiles, reassigning, all of them return Unit

  // Code blocks

  val aCodeBlock = {
    val y = 2
    val z = y + 1

    if (z > 2) "hello" else "goodbye"
  }

  // 1. difference between "hello world" vs println("hello world")? First is a string, second is an unit (side effect)
  // 2.

  val someValue = {
    2 < 3
  } // value of type Boolean (true)

  val someOtherValue = {
    if (someValue) 239 else 986
    42
  } // value of type Int (42, last expr)

}