package lectures.part2oop

object OOBasic extends App {

  val person = new Person("John", 26)
  println(person.x)
  person.greet("Daniel")
  person.greet()

  val imposter = new Writer("Franz", "Kafka", 1890)
  val dosto = new Writer("Fiodor", "Dostoievski", 1910)
  println(dosto.fullName())

  val karamazov = new Novel("Karamazov Brothers", 1980, dosto)
  println(karamazov.authorAge())
  println(karamazov.isWrittenBy(dosto))
  println(karamazov.isWrittenBy(imposter))

  val counter = new Counter

  counter.inc.print
  counter.inc.inc.inc.print
  counter.inc(10).print
}

// constructor
class Person(name: String, val age: Int = 0) {
  val x = 2
  println(1 + 3)

  // method
  def greet(name: String): Unit = println(s"${this.name} says: Hi, $name")

  //overloading
  def greet(): Unit = println(s"Hi, I am $name")

  // multiple constructors
  def this(name: String) = this(name, 0)

}

/*
Novel and a writer

Writer: first name, surname, year
  - method: full name

Novel: name, year of release, author
  - authorAge
  - isWrittenBy(author)
  - copy (new year of release) = new instance of Novel


-----

Counter class
  - receives an int value
  - method current count
  - method increment/decrement => new counter
  - overload inc/dec to receive an amount
 */

class Writer(firstName: String, surname: String, val year: Int) {
  def fullName(): String = s"$firstName $surname"
}

class Novel(name: String, year: Int, author: Writer) {
  def authorAge(): Int = year - author.year
  def isWrittenBy(author: Writer): Boolean = author == this.author
  def copy(newYear: Int): Novel = new Novel(name, newYear, author)
}

class Counter(val count: Int = 0) {
  def inc = {
    println("incrementing")
    new Counter(count + 1)
  } // immutability

  def dec = {
    println("decrementing")
    new Counter(count - 1)
  }

  def inc(n: Int): Counter = {
    if (n <= 0) this
    else inc.inc(n - 1)
  }
  def dec(n: Int): Counter = {
    if (n <= 0) this
    else dec.dec(n - 1)
  }

  def print = println(count)
}