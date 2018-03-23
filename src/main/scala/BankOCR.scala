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
    if(accountNumberAsDigits.contains("?")) accountNumberAsDigits + " ILL"
    else Try(evaluateChecksum(accountNumberAsDigits)) match {
      case Success(_) => accountNumberAsDigits
      case Failure(_) => accountNumberAsDigits + " ERR"
    }
  }

  def evaluateChecksum(input: String): Boolean = {
    val str: List[Int] = input.map(_.toString).map(_.toInt).toList
    val strFormattedAsChecksum = Checksum(str.head,str(1),str(2),str(3),str(4),str(5),str(6),str(7),str(8))
    if(strFormattedAsChecksum.isValid) true else throw new InvalidChecksumException("checksum invalid")
  }

  class InvalidChecksumException(msg: String) extends Exception(msg)

  case class Checksum(nine: Int, eight: Int, seven: Int, six: Int, five: Int, four: Int, three: Int, two: Int, one: Int) {
    def isValid: Boolean = {
      ((one + 2) * (two + 3) * (three + 4) * (four + 5) * (five + 6) * (six + 7) * (seven + 8) * (eight + 9) * nine) % 11 == 0
    }
  }

  case class AccountNumberInput(top: String, middle: String, bottom: String) {

    def digitiser(start: Int, end: Int): String = {
      val num: String = top.substring(start, end) + middle.substring(start, end) + bottom.substring(start, end)
      val tryPattern: Try[String] = Try(pattern(num))

      tryPattern match {
        case Success(_) => pattern(num)
        case Failure(_) => "?"
      }
    }

    val firstDigit: String = digitiser(0, 3)
    val secondDigit: String = digitiser(3, 6)
    val thirdDigit: String = digitiser(6, 9)
    val fourthDigit: String = digitiser(9, 12)
    val fifthDigit: String = digitiser(12, 15)
    val sixthDigit: String = digitiser(15, 18)
    val seventhDigit: String = digitiser(18, 21)
    val eighthDigit: String = digitiser(21, 24)
    val ninthDigit: String = digitiser(24, 27)
    val digits: String = firstDigit + secondDigit + thirdDigit + fourthDigit + fifthDigit + sixthDigit + seventhDigit + eighthDigit + ninthDigit
  }
}
