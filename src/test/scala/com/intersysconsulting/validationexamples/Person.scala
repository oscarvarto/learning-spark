package com.intersysconsulting.validationexamples

import com.intersysconsulting.validation.Validated
import scalaz.syntax.apply._

object Person {
  def apply(name: String, age: Int): Validated[Person] =
    ^(Name(name), Age(age)){ Person.apply }
}

case class Person(name: Name, age: Age)