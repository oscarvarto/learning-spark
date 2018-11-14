package com.intersysconsulting.syntax

import scalaz.std.either.eitherInstance
import scalaz.syntax.bifunctor.ToBifunctorOps
import scalaz.syntax.std.string.ToStringOpsFromString

object string {

  final case class Error(msg: String) extends AnyVal

  // See note in https://leanpub.com/fpmortals/read#leanpub-auto-functionality
  // This more verbose form of the "extension syntax" avoids intermediate class allocation
  implicit final class StringExtraOps(private val s: String) extends AnyVal {
    /** Returns true if not empty and every character is whitespace
      *
      * @note using scala APIs
      */
    def scalaWhitespaceOnly: Boolean =
      s.nonEmpty && s.forall(_.isWhitespace)

    /** Returns true if not empty and every character is whitespace
      *
      * @note using Java APIs
      *
      * @note using lambda syntax for SAM types
      * @see https://www.scala-lang.org/news/2.12.0/#lambda-syntax-for-sam-types
      * @see https://www.scala-lang.org/files/archive/spec/2.12/06-expressions.html#sam-conversion
      */
    def isWhitespaceOnly: Boolean = {
      !s.isEmpty &&
        s.chars().allMatch(Character.isWhitespace(_))
    }

    def intOrError: Either[Error, Int] =
      s.parseInt.toEither.leftMap(nfe => Error(nfe.getMessage()))
  }
}
