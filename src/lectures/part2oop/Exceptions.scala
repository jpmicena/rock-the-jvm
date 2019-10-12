package lectures.part2oop

object Exceptions extends App {

  val x: String = null
  //println(x.length)
  // this ˆ will crash with a NPE
  // throwing and catching exceptions

  // 1. throwing exceptions
  //val aWeirdValue = throw new NullPointerException // NPE is a class

  // throwable classes extend the Throwable class
  // Exception and Error are the major Throwable subtypes

  // 2. how to catch expressions
  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("No Int for you!")
    else 42
   val potentialFail = try {
    // code that might throw
    getInt(false)
  } catch {
    case e: RuntimeException => println("caught a Runtime exception")
  } finally {
     // code that will be executed no matter what
     // optional
     // does not influence the return
     // use finally only for the side effects
    println("finally")
  }

  // 3. how to define your own exceptions
  class MyException extends Exception
  val exception = new MyException

  //throw exception

  /*
    1. Crash your program with a OutOfMemoryError
    2. Crash with SOError
    3. PocketCalculator
        - add(x,y)
        - subtract(x, y)
        - multiply(x, y)
        - divide(x, y)

        Throw
          - OverflowException if add(x, y) exceeds Int.MAX_VALUE
          - UnderflowException if subtract(x, y) exceeds Int.MIN_VALUE
          - MathCalculationException for division by 0
   */

  class OverflowException extends RuntimeException
  class UnderflowException extends RuntimeException
  class MathCalculationException extends Exception("Division By 0")

  object PocketCalculator {
    def add(x: Int, y: Int): Int = {
      val result = x + y
      if(x > 0 && y > 0 && result < 0) throw new OverflowException
      else if(x < 0 && y < 0 && result > 0) throw new UnderflowException
      else result
    }
    def subtract(x: Int, y: Int): Int = {
      val result = x - y
      if(x > 0 && y < 0 && result < 0) throw new OverflowException
      if(x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }
    def multiply(x: Int, y: Int): Int = {
      val result = x * y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      if (x < 0 && y < 0 && result < 0) throw new OverflowException
      if (x > 0 && y < 0 && result > 0) throw new UnderflowException
      if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }
    def divide(x: Int, y: Int): Int = {
      if (y == 0) throw new MathCalculationException
      else x / y
    }
  }

  println(PocketCalculator.divide(2, 0))
}
