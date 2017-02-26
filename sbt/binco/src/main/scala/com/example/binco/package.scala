package com.example

import scala.language.postfixOps

package object binco {

  implicit class BinomialCoefficient(n: Long) {

    def !(): Long = n match {
      case x if x < 0 => throw new IllegalArgumentException("%d is negative" format n)
      case x if x < 2 => 1L
      case _ => (2L to n).reduce((a, b) => a * b)
    }

    def over(k: Long): Long = (n!) / ((k!) * ((n - k)!))
  }
}
