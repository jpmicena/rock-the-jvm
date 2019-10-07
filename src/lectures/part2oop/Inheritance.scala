package lectures.part2oop

object Inheritance extends App {

  // single class inheritance
  // private = you cant call the method from the subclass
  // protected = you can call the method inside the subclass only
  sealed class Animal {
    val creatureType = "Wild"
    def eat: Unit = println("nomnom")
  }

  class Cat extends Animal {
    def crunch = {
      eat
      println("crunch crunch")
    }
  }

  val cat = new Cat
  cat.crunch

  // constructors
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }

  class Adult(name: String, age: Int, idCard: String) extends Person(name)

  // overriding
  class Dog(override val creatureType: String) extends Animal {
    override def eat = {
      super.eat
      println("crunch, crunch")
    }
    //override val creatureType: String = "domestic"
  }

  val dog = new Dog("K9")
  dog.eat
  println(dog.creatureType)

  // type substitution (broad: polymorphism)
  val unknownAnimal: Animal = new Dog("K9")
  unknownAnimal.eat

  // overRIDING vs overLOADING

  // super

  // preventing overrides
  // 1 - use final on member
  // 2 - use final on class (prevents all extensions)
  // 3 - seal the class = extend classes in THIS FILE, prevent extension in other files

}
