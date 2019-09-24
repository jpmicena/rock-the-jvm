package lectures.part1basics

object Recursion extends App {

  def factorial(n: Int): Int =
    if (n <= 1) 1
    else {
      println("Computing Factorial of " + n + " I first need factorial of " + (n-1))
      val result = n * factorial(n-1)
      println("Computed factorial of " + n)

      result
    }

  println(factorial(10))

  def anotherFactorial(n: Int): BigInt = {
    def factHelper(x: BigInt, accumulator: BigInt): BigInt =
      if (x <= 1) accumulator
      else factHelper(x - 1, x * accumulator) // TAIL RECURSION = use recursive call as the last expression

    factHelper(n, 1)
  }

  println(anotherFactorial(10))
  println(anotherFactorial(5000))

  /*
    1. Concatenate a string n times
    2. IsPrime function tail recursive
    3. Fibonacci function, tail recursive
  */

  def concatTailRec(s: String, n: Int): String = {
    def concatHelper(concatenation: String, x: Int): String = {
      if (x == 0) concatenation
      else concatHelper(concatenation + s, (x-1))
    }
    concatHelper("", n)
  }

  def isPrimeTailRec(n: Int): Boolean = {
    def isPrimeUntil(t: Int): Boolean = {
      if (t <= 1) true
      else n % t != 0 && isPrimeUntil(t - 1)
    }
    isPrimeUntil(n/2)
  }

  def fibonacciTailRec(n: Int): BigInt = {
    def fibonacciHelper(t: Int, accN1: BigInt, accN2: BigInt): BigInt = {
      if (t > n) accN1+accN2
      else if (t <= 1) fibonacciHelper(t + 1, 0, 0)
      else if (t == 2) fibonacciHelper(t + 1, 1, 0)
      else fibonacciHelper(t + 1, accN1+accN2, accN1)
    }
    fibonacciHelper(0, 0, 0)
  }

  println(concatTailRec("O", 10))
  println(isPrimeTailRec(12))
  println(isPrimeTailRec(7))
  println(fibonacciTailRec(6))
  println(fibonacciTailRec(7))
  println(fibonacciTailRec(8))
  println(fibonacciTailRec(9))
}

/*
0 = 0
1 = 1
2 = 1
3 = 2
4 = 3
5 = 5
6 = 8
7 = 13
8 = 21
9 = 34
*/
