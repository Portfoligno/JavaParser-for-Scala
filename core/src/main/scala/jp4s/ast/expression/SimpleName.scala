package jp4s.ast
package expression

object SimpleName {
  def apply(identifier: Identifier): SimpleName =
    new SimpleName(identifier.value)

  def unapply(s: SimpleName): Some[Identifier] =
    Some(identifier(s))
}
