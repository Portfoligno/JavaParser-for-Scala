package jp4s.ast.name

import eu.timepit.refined.types.string.NonEmptyString

object Simple {
  def apply(identifier: NonEmptyString): Simple =
    new Simple(identifier.value)

  def unapply(x: Simple): Option[NonEmptyString] =
    Some(NonEmptyString.unsafeFrom(x.getIdentifier))
}
