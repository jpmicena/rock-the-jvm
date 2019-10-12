package lectures.part2oop
import scala.language.postfixOps

object MethodNotations extends App{

  class Person(val name: String, favoriteMovie: String, val age: Int = 20) {
    def likes(movie: String): Boolean = movie == favoriteMovie
    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def +(codename: String): Person = new Person(s"${this.name} $codename", this.favoriteMovie)
    def unary_! : String = s"$name, what the heck?!"
    def unary_+ : Person = new Person(name, favoriteMovie, age + 1)
    def isAlive: Boolean = true
    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie. I'm $age years old"
    def apply(n: Int): String = s"$name watched $favoriteMovie $n times"
    def learns(course: String): String = s"$name learns $course"
    def learnsScala: String = this.learns("Scala")
  }

  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  println(mary likes "Inception") // equivalent
  // infix notation = operator notation

  // "operators" in Scala
  val tom = new Person("Tom", "Fight Club")
  println(mary + tom)
  println(mary.+(tom))

  println(1 + 2)
  println(1.+(2)) // All operators are methods

  // prefix notation
  val x = -1
  val y = 1.unary_- // equivalent
  // unary_ prefix only works with - * ~ !
  println(!mary)
  println(mary.unary_!)

  // postfix notation
  println(mary.isAlive)
  println(mary isAlive)

  // apply
  println(mary.apply())
  println(mary()) // equivalent

  /*
    1. Overload the + operator
       mary + "the rockstar" => new person "Mary (the rockstar)"

    2. Add an age to the person class
       Add an unary + operator => new person with the age + 1

    3. Add a "learns" method in the Person class => "Mary learns Scala"
       Add a learnsScala method, calls the learns method with "Scala"
       use it in postfix notation

    4. Overload the apply method
       mary.apply(2) => "Mary watched Inception 2 times"
   */

  /////
  val maryCodenamed = mary + "the rockstar"
  println(maryCodenamed())
  println((mary + "the rockstar").apply())

  val maryOlder = +mary
  println(maryOlder())

  println(mary learnsScala)

  println(mary.apply(2))

}
