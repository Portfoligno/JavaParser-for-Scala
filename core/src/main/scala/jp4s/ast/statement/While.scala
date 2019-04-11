package jp4s.ast.statement

import jp4s.ast.expression.Expression

object While {
  def apply(condition: Expression, body: Statement): While =
    new While(condition, body)

  def unapply(w: While): Some[(Expression, Statement)] =
    Some((w.getCondition, w.getBody))
}
