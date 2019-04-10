package jp4s.ast.expression

object NullLiteral {
  def apply(): NullLiteral =
    new NullLiteral()

  def unapply(l: NullLiteral): Boolean =
    true
}
