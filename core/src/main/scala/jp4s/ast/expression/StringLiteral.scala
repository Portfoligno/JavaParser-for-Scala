package jp4s.ast.expression

object StringLiteral {
  def apply(value: String): StringLiteral =
    new StringLiteral(value)

  def unapply(l: StringLiteral): Some[String] =
    Some(l.getValue)
}
