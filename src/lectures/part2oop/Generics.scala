package lectures.part2oop

object Generics extends App {

  class MyList[+A] {
    // use the type A inside the class definition
    def add[B >: A](element: B): MyList[B] = ???
    /*
      A = Cat
      B = Animal
      If you add a dog into a list of cat it turns into a list of animals
    */
  }

  class MyMap[Key, Value] {
    // you can have as many type parameters as you want
  }

  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  // generic methods
  object MyList {
    def empty[A]: MyList[A] = ???
  }

  val emptyListOfIntegers = MyList.empty[Int]

  // variance problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // Questions: Does list of cat extends list of animal?
  // 1. yes List[Cat] extends List[Animal] = COVARIANCE
  class CovariantList[+A]
  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  // animalList.add(new Dog) ??? HARD QUESTION

  // 2. no = INVARIANCE
  class InvariantList[A]
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]
  //val invariantAnimalList: InvariantList[Animal] = new InvariantList[Cat] Throw type mismatch

  // 3. Hell no! = CONTRAVARIANCE
  class ContravariantList[-A]
  val contravariantList: ContravariantList[Cat] = new ContravariantList[Animal]

  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal]

  // bounded types
  class Cage[A <: Animal](Animal: A)
  val cage = new Cage(new Dog)

  class Car
  //val newCage = new Cage(new Car) impossible


}
