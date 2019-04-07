package jp4s.ast

object SimpleName {
  def apply(identifier: Identifier): SimpleName =
    new SimpleName(identifier)

  def unapply(s: SimpleName): Some[Identifier] =
    Some(identifier(s))
}
