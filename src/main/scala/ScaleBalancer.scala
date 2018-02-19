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
    val left: List[Int] = filtered.take(2)
    val right: List[Int] = if (filtered.length > 2) filtered.takeRight(filtered.length - 2) else Nil
    val diff: Int = left.max - left.min

    if (left.head == left.tail.head) {
      ""
    } else if (right.contains(diff)) {
      diff.toString
    } else if (right.size > 1) {
      val matches = right.map(x => x + left.head) intersect right.map(x => x + left.tail.head)
      s"${matches.head - left.head},${matches.head - left.tail.head}"
    } else {
      throw new BalanceNotPossibleException
    }
  }

}

