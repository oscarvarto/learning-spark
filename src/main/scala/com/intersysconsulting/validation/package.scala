package com.intersysconsulting

import scalaz.ValidationNel

package object validation {
  type Validated[A] = ValidationNel[ValidationError, A]
}
