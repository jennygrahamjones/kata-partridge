
import scala.io.StdIn


object KensKata extends  App {

  val firstNumber = StdIn.readLine("Please enter first number")
  val secondNumber = StdIn.readLine("Please enter second number")

  def numbers1( nums: String ): Int = {
    nums match {
      case "" => -1
      case _ => nums.toInt
    }
  }

  def numbers2( nums2: String ): Int = {
    nums2 match {
      case "" => -1
      case _ => nums2.toInt
    }
  }

  def division( number1: Int, number2: Int ): String = {
    val result = numbers1(firstNumber) / numbers2(secondNumber)
    val remainder = numbers1(firstNumber) % numbers2(secondNumber)
    result

    s"Number $firstNumber divided by $secondNumber is $result and has remainder $remainder"

  }
    println(division(numbers1(firstNumber),numbers2( secondNumber)))

}