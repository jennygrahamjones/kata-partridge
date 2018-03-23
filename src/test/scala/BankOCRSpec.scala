import org.scalatest.{MustMatchers, WordSpec}

class BankOCRSpec extends WordSpec with MustMatchers {

  "BankOCR" when {

    "readInput is called" must {

      "return 00000000 when given the corresponding input" in {
        val input = " _  _  _  _  _  _  _  _  _ " +
                    "| || || || || || || || || |" +
                    "|_||_||_||_||_||_||_||_||_|"
        BankOCR.readInput(input) mustEqual "000000000"
      }

      "return 111111111 when given the corresponding input" in {
        val input = "                           " +
                    " |  |  |  |  |  |  |  |  | " +
                    " |  |  |  |  |  |  |  |  | "
        BankOCR.readInput(input) mustEqual "111111111"
      }

      "return 123456789 when given the corresponding input" in {
        val input = "    _  _     _  _  _  _  _ " +
                    " |  _| _||_||_ |_   ||_||_|" +
                    " | |_  _|  | _||_|  ||_|  |"
        BankOCR.readInput(input) mustEqual "123456789"
      }

    }

  }

}
