package com.example.binco

import org.scalatest.FlatSpec
import org.scalatest.Matchers

import scala.language.postfixOps

class BinCoTest extends FlatSpec with Matchers {

  "The factorials of 0 and 1" should "be 1" in {
    (0!) shouldBe 1L
    (1!) shouldBe 1L
  }

  "The factorial of 5" should "be 120" in {
    (5!) shouldBe 120L
  }

  "No factorial" should "be negative" in {
    val ex = the[IllegalArgumentException] thrownBy ((-1)!)
    ex.getMessage shouldBe "-1 is negative"
  }

  "The binomial coefficient of 5 and 3" should "be 10" in {
    (5 over 3) shouldBe 10L
  }
}
