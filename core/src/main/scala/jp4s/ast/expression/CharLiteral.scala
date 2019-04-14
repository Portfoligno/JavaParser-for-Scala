package jp4s.ast.expression

object CharLiteral {
  def apply(value: String): CharLiteral =
    new CharLiteral(value)

  def unapply(l: CharLiteral): Some[String] =
    Some(l.getValue)
}
