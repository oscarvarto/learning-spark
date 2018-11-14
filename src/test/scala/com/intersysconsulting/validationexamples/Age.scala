package com.intersysconsulting.validationexamples

import com.intersysconsulting.validation.{Validated, ValidationError, ValidationRules}
import scalaz.NonEmptyList

object Age extends ValidationRules[Age] {
  type E = AgeError

  sealed trait AgeError extends ValidationError
  case object AgeNegative extends AgeError
  case object AgeTooBig extends AgeError

  val MaxAge: Int = Byte.MaxValue.toInt // 127

  override def rules: NonEmptyList[Rule] =
    NonEmptyList(
      mustNot(_.value < 0, AgeNegative),
      mustNot(_.value > MaxAge, AgeTooBig)
    )

  def apply(age: Int): Validated[Age] =
    validate(new Age(age) {})
}

sealed abstract case class Age private(value: Int)