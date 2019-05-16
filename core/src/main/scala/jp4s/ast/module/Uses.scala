package jp4s.ast
package module

import nejc4s.NonEmptyJavaList

object Uses {
  def apply(name: NonEmptyJavaList[Identifier]): Uses =
    new Uses(nameNode(name))

  def unapply(u: Uses): Some[NonEmptyJavaList[Identifier]] =
    Some(identifiers(u.getName))
}
