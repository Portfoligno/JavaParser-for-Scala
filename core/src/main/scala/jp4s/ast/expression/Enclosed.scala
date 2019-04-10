package jp4s.ast.expression

object Enclosed {
  def apply(inner: Expression): Enclosed =
    new Enclosed(inner)

  def unapply(e: Enclosed): Some[Expression] =
    Some(e.getInner)
}
