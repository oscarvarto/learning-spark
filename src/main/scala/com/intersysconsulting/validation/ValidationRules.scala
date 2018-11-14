package com.intersysconsulting.validation

import scalaz.Validation.liftNel
import scalaz.syntax.foldable1._
import scalaz.{Failure, IList, NonEmptyList, Reader, Semigroup, Success}

abstract class ValidationError

trait ValidationRules[A] {
  type E <: ValidationError
  type Rule = Reader[A, Validated[A]]

  def must(predicate: A => Boolean, error: E): Rule =
    Reader { a =>
      if (predicate(a)) Success(a)
      else Failure(NonEmptyList.nel(error, IList.empty))
    }

  def mustNot(predicate: A => Boolean, error: E): Rule =
    Reader { liftNel(_)(predicate, error) }

  def rules: NonEmptyList[Rule]

  implicit val firstSemigroup: Semigroup[A] =
    Semigroup.firstSemigroup

  def validate(candidate: A): Validated[A] =
    rules.sumr1.run(candidate)
}