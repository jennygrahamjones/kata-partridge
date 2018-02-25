/*import java.security.KeyStore.SecretKeyEntry

class BalanceNotPossibleException extends Exception

object ScaleBalancer {


  * A function that will return a String of one or two numbers which
  * represent the values needed to balance a pair of weights on a scale.
  *
  * input format: "[1,2], [4,5,6]"
  *  - [1,2] represents weights to be balanced
  *  - [4,5,6] represents the weights available to achieve this balance
  * */

  /*def balance(input: String): Any {

    val splitString: Array[ Int ] = input.replaceAll("\\],\\[\\]", "").replaceAll("\\[", "").replaceAll("\\]", "").split(",").map(_.toInt)

    val arrayLength = splitString.length

    val firstList: Array[ Int ] = Array(splitString(0), splitString(1))

    val secondList: Array[ Int ] = splitString.slice(2, arrayLength)

    // First test - input [3,4],[]
    if (arrayLength == 2 && firstList(0) != firstList(1)) throw new BalanceNotPossibleException

    // Second test - input [3,3],[]
    if (firstList(0) == firstList(1)) {
      return ""
    }

    // Third test - input [3,4],[10]
    if (secondList.length == 1 && secondList(0) != firstList.max - firstList.min) throw new BalanceNotPossibleException

    // Fourth test - input [3,4],[1]
    if (secondList.contains(firstList.max - firstList.min)) {
      return (firstList.max - firstList.min).toString
    }

    // Fifth test - input [5,9],[1,2,6,7]
    for (i: Int <- secondList
         if secondList.contains(i - (firstList.max - firstList.min))) {
      return i.toString + "," + (i - (firstList.max - firstList.min)).toString
    }
    /*
    // Sixth test - input [4,7],[1,4] ???? Is this wrong?
    for (i : Int <- secondList
         if !secondList.contains(i - (firstList.max - firstList.min))) {
      throw new BalanceNotPossibleException
    }

*/
  }

    //    val splitString: Array[String] = input.split("\\],\\[")
    //
    //    val firstList: Array[Int] = splitString(0).replace("[", "").replace("]","").split(",").map(_.toInt)
    //
    //    val a = firstList(0)
    //    val b = firstList(1)
    //
    //    if (splitString(1).toString == "]") throw new BalanceNotPossibleException
    //
    //    val secondList: Array[Int] = splitString(1).replace("]", "").split(",").map(_.toInt)




    //    if (a != b){
    //      throw new BalanceNotPossibleException
    //    }

    //    val arrayLength = secondList.length
    //
    //    print(secondList.foreach(println(_)))
    //    println(arrayLength)
    //
    ////    println(firstList(0))
    ////    println(firstList(1))
    ////    println(secondList.length)
    //
    //    val x = if (secondList.contains(firstList.max - firstList.min)){
    //      (firstList.max - firstList.min).toString
    //    } else { "" }
    //    x


}
*/