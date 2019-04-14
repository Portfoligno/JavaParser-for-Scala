package jp4s.ast.expression

import com.github.javaparser.ast.expr.AssignExpr
import com.github.javaparser.ast.expr.AssignExpr.Operator._

object Assign {
  type Operator = AssignExpr.Operator

  object Plain extends Factory(ASSIGN)
  object Plus extends Factory(PLUS)
  object Minus extends Factory(MINUS)
  object Multiply extends Factory(MULTIPLY)
  object Divide extends Factory(DIVIDE)
  object BinaryAnd extends Factory(BINARY_AND)
  object BinaryOr extends Factory(BINARY_OR)
  object Xor extends Factory(XOR)
  object Remainder extends Factory(REMAINDER)
  object LeftShift extends Factory(LEFT_SHIFT)
  object SignedRightShift extends Factory(SIGNED_RIGHT_SHIFT)
  object UnsignedRightShift extends Factory(UNSIGNED_RIGHT_SHIFT)


  sealed abstract class Factory(val operator: Operator) {
    def apply(target: Expression, value: Expression): Assign =
      new Assign(target, value, operator)

    def unapply(a: Assign): Option[(Expression, Expression)] =
      if (a.getOperator == operator) {
        Some((a.getTarget, a.getValue))
      } else {
        None
      }
  }
}
