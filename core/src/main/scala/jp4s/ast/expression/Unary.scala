package jp4s.ast.expression

import com.github.javaparser.ast.expr.UnaryExpr
import com.github.javaparser.ast.expr.UnaryExpr.Operator._

object Unary {
  type Operator = UnaryExpr.Operator

  object Plus extends Factory(PLUS)
  object Minus extends Factory(MINUS)
  object PrefixIncrement extends Factory(PREFIX_INCREMENT)
  object PrefixDecrement extends Factory(PREFIX_DECREMENT)
  object LogicalComplement extends Factory(LOGICAL_COMPLEMENT)
  object BitwiseComplement extends Factory(BITWISE_COMPLEMENT)
  object PostfixIncrement extends Factory(POSTFIX_INCREMENT)
  object PostfixDecrement extends Factory(POSTFIX_DECREMENT)


  def unapply(u: Unary): Some[Expression] =
    Some(u.getExpression)


  private[expression]
  class Factory(operator: Operator) {
    def apply(expression: Expression): Unary =
      new Unary(expression, operator)

    def unapply(a: Unary): Option[Expression] =
      if (a.getOperator == operator) {
        Unary.unapply(a)
      } else {
        None
      }
  }
}
