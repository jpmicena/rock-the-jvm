package lectures.part3fp

object TuplesAndMaps extends App {

  // tuples = finite ordered "lists"
  val aTuple = (2, "hello, Scala") // Tuple2[Int, String] = (Int, String)

  println(aTuple._1) // 2
  println(aTuple.copy(_2 = "goodbye Java"))
  println(aTuple.swap) // ("hello, Scala", 2)

  // Maps - keys -> values
  val aMap: Map[String, Int] = Map()

  val phoneBook: Map[String, Int] = Map("Jim" -> 555, "Daniel" -> 789).withDefaultValue(-1)
  println(phoneBook)

  // Map ops
  println(phoneBook.contains("Jim"))
  println(phoneBook("Jim"))
  println(phoneBook("Mary"))

  // add a pairing
  val newPairing = "Mary" -> 678
  val newPhoneBook = phoneBook + newPairing
  println(newPhoneBook)

  // functionals on maps
  // map, flatMap, filter

  //println(phoneBook.map(pair => pair._1.toLowerCase -> pair._2))

  // filterKeys
  println(phoneBook.view.filterKeys(x => x.startsWith("J")).toMap)
  // mapValues
  println(phoneBook.view.mapValues(number => "0245-" + 10).toMap)

  // conversions
  println(phoneBook.toList)
  println(List(("Daniel", 555)).toMap)

  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(name => name.charAt(0)))

  /*
    1. What would happen if had two original entries "Jim" -> 555 and "JIM" -> 900

      !!! careful with mapping keys !!!

    2. Overly simplified social network based on maps
      Person = String
        - add a person to the network
        - remove
        - friend (mutual)
        - unfriend (mutual)

        - number of friends of a person
        - person with most friends
        - how many people have no friends
        - if there is a social connection between two people (direct or not)
   */

  def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] =
    network + (person -> Set())

  def friend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] =
      network + (a -> (network(a) + b)) + (b -> (network(b) + a))

  def unfriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] =
    network + (a -> (network(a) - b)) + (b -> (network(b) - a))

  def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    @scala.annotation.tailrec
    def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] = {
      if (friends.isEmpty) networkAcc
      else removeAux(friends.tail, unfriend(networkAcc, person, friends.head))
    }

    val unfriended = removeAux(network(person), network)
    unfriended - person
  }

  val empty: Map[String, Set[String]] = Map()
  val network = add(add(empty, "Bob"),"Mary")

  println(network)
  println(friend(network, "Bob", "Mary"))
  println(unfriend(friend(network, "Bob", "Mary"), "Bob", "Mary"))
  println(remove(friend(network, "Bob", "Mary"), "Bob"))

  //Jim, Bob, Mary
  val people = add(add(add(empty, "Bob"),"Mary"), "Jim")
  val jimBob = friend(people, "Bob", "Jim")
  val testNet = friend(jimBob, "Mary", "Bob")

  println(testNet)

  def nFriends(network: Map[String, Set[String]], person: String): Int = {
    if (!network.contains(person)) 0
    else network(person).size
  }

  def mostFriends(network: Map[String, Set[String]]): String = {
//    val t = network.reduceLeft { (x, y) => if (x._2.size > y._2.size) x else y }
//    t._1
    network.maxBy(pair => pair._2.size)._1
  }

  def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int = {
    //network.filter(x => x._2.isEmpty).size
    //network.count(x => x._2.isEmpty)
    //network.count(_._2.isEmpty)
    network.view.filterKeys(k => network(k).isEmpty).toMap.size
  }

  def socialConnection(network: Map[String, Set[String]], personA: String, personB: String): Boolean = {
    @scala.annotation.tailrec
    def bfs(target: String, consideredPeople: Set[String], discoverPeople: Set[String]): Boolean = {
      if (discoverPeople.isEmpty) false
      else {
        val person = discoverPeople.head
        if (person == target) true
        else if (consideredPeople.contains(person)) bfs(target, consideredPeople, discoverPeople.tail)
        else bfs(target, consideredPeople + person, discoverPeople.tail ++ network(person))
      }
    }

    bfs(personB, Set(), network(personA) + personA)
  }

  println(nFriends(testNet, "Bob"))
  println(mostFriends(testNet))
  println(nPeopleWithNoFriends(testNet))
  println(nPeopleWithNoFriends(people))
  println(socialConnection(testNet, "Mary", "Jim"))
  println(socialConnection(network, "Mary", "Bob"))

}
