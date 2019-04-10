package jp4s.ast.expression

object Conditional {
  def apply(condition: Expression, thenExpr: Expression, elseExpr: Expression): Conditional =
    new Conditional(condition, thenExpr, elseExpr)

  def unapply(c: Conditional): Some[(
    Expression,
    Expression,
    Expression
  )] =
    Some((
      c.getCondition,
      c.getThenExpr,
      c.getElseExpr
    ))
}
