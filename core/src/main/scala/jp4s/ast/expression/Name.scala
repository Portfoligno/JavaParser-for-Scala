package jp4s.ast
package expression

object Name {
  def apply(name: Identifier): Name =
    new Name(simpleNameNode(name))

  def unapply(n: Name): Some[Identifier] =
    Some(identifier(n.getName))
}
