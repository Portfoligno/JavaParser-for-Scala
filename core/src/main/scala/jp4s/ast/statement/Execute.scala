package jp4s.ast.statement

import jp4s.ast.expression.Expression

object Execute {
  def apply(expression: Expression): Execute =
    new Execute(expression)

  def unapply(e: Execute): Some[Expression] =
    Some(e.getExpression)
}
