object BankOCR {

  val pattern = Map(" _ " +
                    "| |" +
                    "|_|" -> "0",
                    "   " +
                    " | " +
                    " | " -> "1",
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
                    "  |" -> "9"
                    )

  case class CR(top: String, middle: String, bottom: String) {
    override def toString: String = top + middle + bottom

    def digitiser(start: Int, end: Int): String =
      top.substring(start,end) + middle.substring(start,end) + bottom.substring(start,end)

    val firstDigit: String = digitiser(0,3)
    val secondDigit: String = digitiser(3,6)
    val thirdDigit: String = digitiser(6,9)
    val fourthDigit: String = digitiser(9,12)
    val fifthDigit: String = digitiser(12,15)
    val sixthDigit: String = digitiser(15,18)
    val seventhDigit: String = digitiser(18,21)
    val eighthDigit: String = digitiser(21,24)
    val ninthDigit: String = digitiser(24,27)

    val digits: String = pattern(this.firstDigit) + pattern(this.secondDigit) + pattern(this.thirdDigit) +
      pattern(this.fourthDigit) + pattern(this.fifthDigit) + pattern(this.sixthDigit) +
      pattern(this.seventhDigit) + pattern(this.eighthDigit) + pattern(this.ninthDigit)
  }

  def readInput(input: String): String = {
    CR(input.substring(0, 27), input.substring(27, 54), input.substring(54, 81)).digits
  }

}
