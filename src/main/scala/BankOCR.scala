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
    val strFormattedAsChecksum = Checksum(str.head, str(1), str(2), str(3), str(4), str(5), str(6), str(7), str(8))

    if (strFormattedAsChecksum.isValid) {
      true
    } else {
      throw new InvalidChecksumException("checksum invalid")
    }
  }

  class InvalidChecksumException(msg: String) extends Exception(msg)

  case class Checksum(nine: Int, eight: Int, seven: Int, six: Int, five: Int, four: Int, three: Int, two: Int, one: Int) {
    def isValid: Boolean = {
      ((one + 2) * (two + 3) * (three + 4) * (four + 5) * (five + 6) * (six + 7) * (seven + 8) * (eight + 9) * nine) % 11 == 0
    }
  }

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
