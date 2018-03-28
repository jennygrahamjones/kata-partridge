import scala.util.{Failure, Success, Try}

object BankOCR {

  val pattern = Map(" _ " +
    "| |" +
    "|_|" -> "0",
    "   " +
      " | " +
      " | " -> "1",
    "   " +
      "  |" +
      "  |" -> "1",
    " _ " +
      " _|" +
      "|_ " -> "2",
    " _ " +
      " _|" +
      " _|" -> "3",
    "   " +
      "|_|" +
      "  |" -> "4",
    " _ " +
      "|_ " +
      " _|" -> "5",
    " _ " +
      "|_ " +
      "|_|" -> "6",
    " _ " +
      "  |" +
      "  |" -> "7",
    " _ " +
      "|_|" +
      "|_|" -> "8",
    " _ " +
      "|_|" +
      " _|" -> "9"
  )

  def readInput(input: String): String = {
    val accountNumberAsDigits: String = AccountNumberInput(input.substring(0, 27), input.substring(27, 54), input.substring(54, 81)).digits
    if (accountNumberAsDigits.contains("?")) {
      accountNumberAsDigits + " ILL"
    } else {
      Try(evaluateChecksum(accountNumberAsDigits)) match {
        case Success(_) => accountNumberAsDigits
        case Failure(_) => accountNumberAsDigits + " ERR"
      }
    }
  }

  def evaluateChecksum(input: String): Boolean = {
    val str: List[Int] = input.map(_.toString).map(_.toInt).toList
    val productForEvaluation = str.reverse.zipWithIndex.map(x => x._1 * (x._2 + 1)).sum

    if (productForEvaluation % 11 == 0) {
      true
    } else {
      throw new InvalidChecksumException("checksum invalid")
    }
  }

  class InvalidChecksumException(msg: String) extends Exception(msg)

  case class AccountNumberInput(top: String, middle: String, bottom: String) {

    def digits: String = {
      val combinedDigits = for (positionInRow <- 0 until 27 by 3) yield {
        val individualDigit = digitiser(positionInRow, positionInRow + 3)
        individualDigit
      }
      combinedDigits.mkString
    }

    def digitiser(start: Int, end: Int): String = {
      val num: String = top.substring(start, end) + middle.substring(start, end) + bottom.substring(start, end)
      val tryPattern: Try[String] = Try(pattern(num))

      tryPattern match {
        case Success(x) => x
        case Failure(_) => "?"
      }
    }

  }

}
