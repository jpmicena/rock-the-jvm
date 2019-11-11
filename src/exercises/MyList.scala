package exercises

abstract class MyList[+A] {
   /*
      head => first element of the list
      tail => remainder of the list
      isEmpty => is this list empty
      add(int) => new list with this element added
      toString => returns a string representation
    */
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]
  def printElements: String // Polymorphic call
  override def toString: String = "[" + printElements + "]"

  def map[B](transformer: A => B): MyList[B]
  def flatMap[B](transformer: A => MyList[B]): MyList[B]
  def filter(predicate: A => Boolean): MyList[A]

  def  ++[B >: A](list: MyList[B]): MyList[B]
}

case object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyList[B] = Cons(element, Empty)
  def printElements: String = ""

  def map[B](transformer: Nothing => B): MyList[B] = Empty
  def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty
  def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty

  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyList[B] = Cons(element, this)
  def printElements: String = {
    if(t.isEmpty) "" + h
    else h + " " + t.printElements
  }

  def filter(predicate: A => Boolean): MyList[A] = {
    if (predicate(h)) Cons(h, t.filter(predicate))
    else t.filter(predicate)
  }

  def map[B](transformer: A => B): MyList[B] = {
    Cons(transformer(h), t.map(transformer))
  }
  def  ++[B >: A](list: MyList[B]): MyList[B] = Cons(h, t ++ list)

  def flatMap[B](transformer: A => MyList[B]): MyList[B] =
    transformer(h) ++ t.flatMap(transformer)
}


object ListTest extends App {
  val listOfIntegers: MyList[Int] = Cons(1, Cons(2, Cons(3, Empty)))
  val cloneListOfIntegers: MyList[Int] = Cons(1, Cons(2, Cons(3, Empty)))
  val anotherListOfIntegers: MyList[Int] = Cons(4, Cons(5, Empty))
  val listOfStrings: MyList[String] = Cons("Hello", Cons("Scala", Empty))

  println(listOfIntegers.toString)
  println(listOfStrings.toString)

  println(listOfIntegers.map(_ * 2).toString)

  println(listOfIntegers.filter(_ == 0).toString)

  println((listOfIntegers ++ anotherListOfIntegers).toString)

  println(listOfIntegers.flatMap((elem: Int) => Cons(elem, Cons(elem + 1, Empty))).toString)

  println(cloneListOfIntegers == listOfIntegers)

  val combinations = for {
    n <- listOfIntegers
    string <- listOfStrings
  } yield n + "-" + string

  println(combinations)
}