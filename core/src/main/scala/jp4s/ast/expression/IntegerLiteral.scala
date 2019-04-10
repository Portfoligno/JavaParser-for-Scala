package jp4s.ast.expression

object IntegerLiteral {
  def apply(value: String): IntegerLiteral =
    new IntegerLiteral(value)

  def unapply(l: IntegerLiteral): Some[String] =
    LiteralByString.unapply(l)
}
