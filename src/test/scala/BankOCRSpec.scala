import BankOCR.InvalidChecksumException
import org.scalatest.{MustMatchers, WordSpec}

class BankOCRSpec extends WordSpec with MustMatchers {

  "BankOCR" when {

    "readInput is called" must {

      "return 00000000 when given the corresponding input" in {
        val input = " _  _  _  _  _  _  _  _  _ " +
          "| || || || || || || || || |" +
          "|_||_||_||_||_||_||_||_||_|" +
          "                           "
        BankOCR.readInput(input) mustEqual "000000000"
      }

      "return 123456789 when given the corresponding input" in {
        val input = "    _  _     _  _  _  _  _ " +
          " |  _| _||_||_ |_   ||_||_|" +
          " | |_  _|  | _||_|  ||_| _|" +
          "                           "
        BankOCR.readInput(input) mustEqual "123456789"
      }

      "return 111111111 ERR when given the corresponding input" in {
        val input = "                           " +
          " |  |  |  |  |  |  |  |  | " +
          " |  |  |  |  |  |  |  |  | " +
          "                           "
        BankOCR.readInput(input) mustEqual "111111111 ERR"
      }

      "return '49006771? ILL' when given the corresponding input" in {
        val input = "    _  _  _  _  _  _     _ " +
          "|_||_|| || ||_   |  |  | _ " +
          "  | _||_||_||_|  |  |  | _|" +
          "                           "
        BankOCR.readInput(input) mustEqual "49006771? ILL"
      }

      "return 1234?678? ILL when given the corresonding input" in {
        val input = "    _  _     _  _  _  _  _ " +
          "  | _| _||_| _ |_   ||_||_|" +
          "  ||_  _|  | _||_|  ||_| _ " +
          "                           "
        BankOCR.readInput(input) mustEqual "1234?678? ILL"
      }

    }

    "checkSum is called" must {

      "return true if the account number (123456789) has a valid checksum" in {
        val input = "123456789"
        BankOCR.evaluateChecksum(input) mustEqual true
      }

      "return false if the account number (232323235) does not have a valid checksum" in {
        val input = "232323235"
        intercept[InvalidChecksumException] {
          BankOCR.evaluateChecksum(input)
        }
      }

      "return false if the account number (111111111) does not have a valid checksum" in {
        val input = "111111111"
        intercept[InvalidChecksumException] {
          BankOCR.evaluateChecksum(input)
        }
      }

    }

  }

}
