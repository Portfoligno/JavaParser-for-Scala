package jp4s.ast.statement

import jp4s.ast.expression.Expression

object Synchronized {
  def apply(
    expression: Expression,
    body: Block
  ): Synchronized =
    new Synchronized(
      expression,
      body
    )

  def unapply(s: Synchronized): Some[(
    Expression,
    Block
  )] =
    Some((s.getExpression, s.getBody))
}
