import org.scalatest.{MustMatchers, WordSpec}

class ExamResultsSpec extends WordSpec with MustMatchers {

  "checkExamResults" must {

    val correctAnswers = List("a","b","c","d")

    "return 0 when given all blank answers" in {
      ExamResults.checkExamResults(correctAnswers, List("","","","")) mustEqual 0
    }

    "return 16 when all answers given are correct" in {
      ExamResults.checkExamResults(correctAnswers, correctAnswers) mustEqual 16
    }

    "return -4 when all answers given are in correct" in {
      ExamResults.checkExamResults(correctAnswers, correctAnswers.reverse)
    }

    "return 1 when given only one correct answer and 3 incorrect answers" in {
      val answers = List("a","a","a","a")
      ExamResults.checkExamResults(correctAnswers, answers) mustEqual 1
    }

    "return 2 when given 1 correct answer, 2 incorrect and 1 blank answer" in {
      val answers = List("a", "a", "a", "")
      ExamResults.checkExamResults(correctAnswers, answers) mustEqual 2
    }

  }

}