package jp4s.ast.expression

object LongLiteral {
  def apply(value: String): LongLiteral =
    new LongLiteral(value)

  def unapply(l: LongLiteral): Some[String] =
    Some(l.getValue)
}
