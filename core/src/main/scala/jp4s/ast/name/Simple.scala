package jp4s.ast.name

import com.github.javaparser.ast.expr.SimpleName
import eu.timepit.refined.types.string.NonEmptyString

object Simple {
  def apply(identifier: NonEmptyString): Simple =
    new SimpleName(identifier.value)

  def unapply(x: Simple): Option[NonEmptyString] =
    Some(NonEmptyString.unsafeFrom(x.getIdentifier))
}
