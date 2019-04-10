package jp4s.ast.expression

object LiteralByString {
  def unapply(l: LiteralByString): Some[String] =
    Some(l.getValue)
}
