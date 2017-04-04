package com.example.binco.eclipse.test

import org.scalatest.FlatSpec
import org.scalatest.Matchers

import com.example.binco.BinomialCoefficient

class BinCoTest extends FlatSpec with Matchers {
  
  "The binomial coefficient of 5 and 3" should "be 10" in {
    (5 over 3) shouldBe 10L
  }
}
