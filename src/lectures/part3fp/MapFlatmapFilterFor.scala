package lectures.part3fp

object MapFlatmapFilterFor extends App {

  val list = List(1, 2, 3)
  println(list)

  println(list.map(_ + 1))
  println(list.map(_ + "is a number"))

  println(list.filter(_ % 2 == 0))

  val toPair = (x: Int) => List(x, x + 1)
  println(list.flatMap(toPair))


  // print all combinations between two/three lists
  // "iterations"
  val numbers = List(1, 2, 3, 4)
  val chars = List('a', 'b', 'c', 'd')
  val colors = List("black", "white")

  println(numbers.flatMap(n => chars.map(c => c.toString + n.toString)))
  println(numbers.flatMap(n => chars.flatMap(c => colors.map(colors => c.toString + n.toString + colors))))

  // foreach
  list.foreach(println)

  // for-comprehensions
  val forCombinations = for {
    n <- numbers if n % 2 == 0
    c <- chars
    color <- colors
  } yield c.toString + n.toString + color
  println(forCombinations)

  for {
    n <- list
  } println(n)

  // syntax overload
  list.map { x => x * 2 }

  /*
    1. MyList supports for comprehensions?
        map(f: A => B) => MyList[B]
        filter(p: A => Boolean) => MyList[A]
        flatMap(f: A => MyList[B]) => MyList[B]
    2. A small collection of at most ONE element - Maybe[+T]
      - map, flatMap, filter
  */

}
