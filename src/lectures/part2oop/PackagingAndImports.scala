package lectures.part2oop

import playground.{Cinderella => Princess, PrinceCharming} //._
import java.util.Date
import java.sql.{Date => SQLDate}

object PackagingAndImports extends App {

  // package members are accessible by their simple name
  val writer = new Writer("Daniel", "RockTheJVM", 2018)

  val princess = new Princess // playground.Cinderella = fully qualified class name

  // Packages are ordered in hierarchy
  // matching folder structure

  // package object
  sayHello
  println(SPEED_OF_LIGHT)

  // imports
  val prince = new PrinceCharming

}

