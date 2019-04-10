package jp4s.ast.expression

object FloatingPointLiteral {
  def apply(value: String): FloatingPointLiteral =
    new FloatingPointLiteral(value)

  def unapply(l: FloatingPointLiteral): Some[String] =
    LiteralByString.unapply(l)
}
