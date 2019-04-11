package jp4s.ast.statement

import jp4s.ast.expression.Expression

object Throw {
  def apply(expression: Expression): Throw =
    new Throw(expression)

  def unapply(t: Throw): Some[Expression] =
    Some(t.getExpression)
}
