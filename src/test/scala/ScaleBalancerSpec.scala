import org.scalatest.{MustMatchers, WordSpec}

class ScaleBalancerSpec extends WordSpec with MustMatchers {

  "balance" must {

    "throw a BalanceNotPossibleException when given input '[3,4], []'" in {
      intercept[BalanceNotPossibleException] {
        ScaleBalancer.balance("[3,4],[]")
      }
      "return '' when given the input of [3,3]" in {
        ScaleBalancer.balance("[3,3],[]") mustEqual ""
      }
    }

    "throw a BalanceNotPossibleException when given input '[3,4], [10]'" in {
      intercept[BalanceNotPossibleException] {
        ScaleBalancer.balance("[3,4],[10]")
      }
    }

    "return 1 when given the input '[3,4],[1]'" in {
      ScaleBalancer.balance("[3,4],[1]") mustEqual "1"
    }

    "return '6,2' when given the input '[5, 9], [1, 2, 6, 7]'" in {
      ScaleBalancer.balance("[5,9],[1,2,6,7]") mustEqual "6,2"
    }

    "throw a BalanceNotPossibleException when given the input '[4,7], [1,4]" in {
      intercept[BalanceNotPossibleException] {
        ScaleBalancer.balance("[4,7], [2,4]")
      }
    }
  }
}




