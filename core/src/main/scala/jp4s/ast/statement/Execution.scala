package jp4s.ast.statement

import com.github.javaparser.ast.expr.Expression

object Execution {
  def apply(expression: Expression): Execution =
    new Execution(expression)

  def unapply(e: Execution): Some[Expression] =
    Some(e.getExpression)
}
