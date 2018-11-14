package com.intersysconsulting.refinedexamples

import eu.timepit.refined.W
import eu.timepit.refined.api.Refined
import eu.timepit.refined.auto._
import eu.timepit.refined.boolean.{And, Not}
import eu.timepit.refined.char.Whitespace
import eu.timepit.refined.collection.{Forall, NonEmpty}
import eu.timepit.refined.numeric.Interval

// A person must have a NonEmpty name and a non-negative age.
object RefinedExample {
  type AgeAux = Interval.Closed[W.`0`.T, W.`127`.T]

  final case class Age(n: Int Refined AgeAux)

  type NameAux = NonEmpty And Not[Forall[Whitespace]]

  final case class Name(value: String Refined NameAux)

  final case class Person(name: Name, age: Age)

  val spiderman = Person(Name("spiderman"), Age(25))
}