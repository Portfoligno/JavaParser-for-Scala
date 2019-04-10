package jp4s.ast.expression

object ArrayAccess {
  def apply(name: Expression, index: Expression): ArrayAccess =
    new ArrayAccess(name, index)

  def unapply(a: ArrayAccess): Some[(Expression, Expression)] =
    Some((a.getName, a.getIndex))
}
