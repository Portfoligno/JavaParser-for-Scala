package jp4s.ast.expression

import com.github.javaparser.ast.expr.AssignExpr
import com.github.javaparser.ast.expr.AssignExpr.Operator._

import scala.collection.mutable

object Assign {
  type OperatorEnum = AssignExpr.Operator

  case object Plain extends Operator(ASSIGN)
  case object Plus extends Operator(PLUS)
  case object Minus extends Operator(MINUS)
  case object Multiply extends Operator(MULTIPLY)
  case object Divide extends Operator(DIVIDE)
  case object BinaryAnd extends Operator(BINARY_AND)
  case object BinaryOr extends Operator(BINARY_OR)
  case object Xor extends Operator(XOR)
  case object Remainder extends Operator(REMAINDER)
  case object LeftShift extends Operator(LEFT_SHIFT)
  case object SignedRightShift extends Operator(SIGNED_RIGHT_SHIFT)
  case object UnsignedRightShift extends Operator(UNSIGNED_RIGHT_SHIFT)


  def apply(operator: Operator, target: Expression, value: Expression): Assign =
    operator(target, value)

  def unapply(a: Assign): Some[(
    Operator,
    Expression,
    Expression
  )] =
    Some((
      Operator(a.getOperator),
      a.getTarget,
      a.getValue
    ))


  private
  val lookup = mutable.Map[OperatorEnum, Operator]()

  object Operator {
    // Ensure all cases are initialized
    Plain
    Plus
    Minus
    Multiply
    Divide
    BinaryAnd
    BinaryOr
    Xor
    Remainder
    LeftShift
    SignedRightShift
    UnsignedRightShift

    def apply(enum: OperatorEnum): Operator =
      lookup(enum)

    def unapply(o: Operator): Some[OperatorEnum] =
      Some(o.enum)
  }

  sealed abstract class Operator(private val enum: OperatorEnum) {
    lookup += enum -> this

    def apply(target: Expression, value: Expression): Assign =
      new Assign(target, value, enum)

    def unapply(a: Assign): Option[(Expression, Expression)] =
      if (a.getOperator == enum) {
        Some((a.getTarget, a.getValue))
      } else {
        None
      }
  }
}
