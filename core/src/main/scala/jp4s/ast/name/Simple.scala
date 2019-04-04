package jp4s.ast.name

import jp4s.ast.Identifier

object Simple {
  def apply(identifier: Identifier): Simple =
    new Simple(identifier.value)

  def unapply(s: Simple): Some[Identifier] =
    Some(identifier(s))


  private[ast]
  def identifier(s: Simple): Identifier =
    Identifier.unsafeFrom(s.getIdentifier)
}
