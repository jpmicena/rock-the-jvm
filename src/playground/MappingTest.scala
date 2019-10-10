package playground

import java.sql.Date
import scala.util.Sorting

object AccountMapping {

  case class BookAccount(name: String, date: Date, numberSAP: String)

  def findLastValid(x: BookAccount, y:BookAccount): BookAccount = {
    if (x.date.compareTo(y.date) >= 0) x else y
  }

  def getAccountNumberByName(name: String): String = {
    bookAccounts
      .filter(x => x.name == name)
      .reduceLeft(findLastValid)
      .numberSAP
  }

  def getAccountNameByNumber(number: String): String = {
    bookAccounts
      .filter(x => x.numberSAP == number)
      .reduceLeft(findLastValid)
      .name
  }

  val bookAccounts = Array(
                          BookAccount("asset__loan",          Date.valueOf("2019-01-01"), "000000000"),
                          BookAccount("asset__loan",          Date.valueOf("2019-01-10"), "111111111"),
                          BookAccount("asset__blablabla",     Date.valueOf("2019-01-01"), "222222222"),
                          BookAccount("asset_teste123",       Date.valueOf("2019-01-29"), "333333333")
                          )
}

object Testes extends App {
  println(AccountMapping.getAccountNumberByName("asset_f_loan"))
}
