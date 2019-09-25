package lectures.part2oop

object OOBasic extends App {

  val person = new Person("John", 26)
  println(person.x)
  person.greet("Daniel")
  person.greet()

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

// class parameters are NOT FIELDS. Use val to convert in fields