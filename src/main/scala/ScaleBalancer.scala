class BalanceNotPossibleException extends Exception

object ScaleBalancer {

  /*
  * A function that will return a String of one or two numbers which
  * represent the values needed to balance a pair of weights on a scale.
  *
  * input format: "[1,2], [4,5,6]"
  *  - [1,2] represents weights to be balanced
  *  - [4,5,6] represents the weights available to achieve this balance
  * */

  def balance(input: String): String = {
    val unwanted: List[String] = List("[", "]")
    val filtered = input.filterNot(n => unwanted.contains(n.toString)).split(",").toList.map(_.toInt)
    val scales: List[Int] = filtered.take(2)
    val weights: List[Int] = if (filtered.length > 2) filtered.takeRight(filtered.length - 2) else Nil
    val diff: Int = scales.max - scales.min

    if (scales.head == scales(1)) {
      ""
    } else if (weights.contains(diff)) {
      diff.toString
    } else if (weights.size > 1) {
      val matches = weights.map(_ + scales.head) intersect weights.map(_ + scales.tail.head)
      s"${matches.head - scales.head},${matches.head - scales.tail.head}"
    } else {
      throw new BalanceNotPossibleException
    }
  }

}

