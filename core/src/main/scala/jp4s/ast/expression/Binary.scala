package jp4s.ast.expression

import com.github.javaparser.ast.expr.BinaryExpr
import com.github.javaparser.ast.expr.BinaryExpr.Operator._

object Binary {
  type Operator = BinaryExpr.Operator

  object Or extends Factory(OR)
  object And extends Factory(AND)
  object BinaryOr extends Factory(BINARY_OR)
  object BinaryAnd extends Factory(BINARY_AND)
  object Xor extends Factory(XOR)
  object Equals extends Factory(EQUALS)
  object NotEquals extends Factory(NOT_EQUALS)
  object Less extends Factory(LESS)
  object Greater extends Factory(GREATER)
  object LessEquals extends Factory(LESS_EQUALS)
  object GreaterEquals extends Factory(GREATER_EQUALS)
  object LeftShift extends Factory(LEFT_SHIFT)
  object SignedRightShift extends Factory(SIGNED_RIGHT_SHIFT)
  object UnsignedRightShift extends Factory(UNSIGNED_RIGHT_SHIFT)
  object Plus extends Factory(PLUS)
  object Minus extends Factory(MINUS)
  object Multiply extends Factory(MULTIPLY)
  object Divide extends Factory(DIVIDE)
  object Remainder extends Factory(REMAINDER)


  def unapply(b: Binary): Some[(Expression, Expression)] =
    Some((b.getLeft, b.getRight))


  private[expression]
  class Factory(val operator: Operator) {
    def apply(left: Expression, right: Expression): Binary =
      new Binary(left, right, operator)

    def unapply(a: Binary): Option[(Expression, Expression)] =
      if (a.getOperator == operator) {
        Binary.unapply(a)
      } else {
        None
      }
  }
}
