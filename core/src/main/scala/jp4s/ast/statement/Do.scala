package jp4s.ast.statement

import jp4s.ast.expression.Expression

object Do {
  def apply(body: Statement, condition: Expression): Do =
    new Do(body, condition)

  def unapply(d: Do): Some[(Statement, Expression)] =
    Some((d.getBody, d.getCondition))
}
