package com.intersysconsulting.validationexamples

import com.intersysconsulting.validation.{Validated, ValidationError, ValidationRules}
import scalaz.NonEmptyList
import com.intersysconsulting.syntax.string.StringExtraOps

object Name extends ValidationRules[Name] {
  type E = NameError

  sealed trait NameError extends ValidationError
  case object NameCannotBeEmpty extends NameError
  case object NameCannotBeWhitespaceOnly extends NameError

  override def rules: NonEmptyList[Rule] =
    NonEmptyList(
      mustNot(_.value.isWhitespaceOnly,
        NameCannotBeWhitespaceOnly),
      mustNot(_.value.isEmpty, NameCannotBeEmpty)
    )

  def apply(s: String): Validated[Name] =
    validate(new Name(s) {})
}

sealed abstract case class Name private(value: String)
