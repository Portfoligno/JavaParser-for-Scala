package jp4s

import java.util

import scala.language.implicitConversions

package object utility {
  type Optional[A] = java.util.Optional[A]
  type JavaList[A] = util.List[A]


  implicit def toOptionalOps[A](optional: Optional[A]): OptionalOps[A] =
    new OptionalOps[A](optional)
}
