package jp4s.ast.expression

import com.github.javaparser.ast.expr.BinaryExpr
import com.github.javaparser.ast.expr.BinaryExpr.Operator._

import scala.collection.mutable

object Binary {
  type OperatorEnum = BinaryExpr.Operator

  case object Or extends Operator(OR)
  case object And extends Operator(AND)
  case object BinaryOr extends Operator(BINARY_OR)
  case object BinaryAnd extends Operator(BINARY_AND)
  case object Xor extends Operator(XOR)
  case object Equals extends Operator(EQUALS)
  case object NotEquals extends Operator(NOT_EQUALS)
  case object Less extends Operator(LESS)
  case object Greater extends Operator(GREATER)
  case object LessEquals extends Operator(LESS_EQUALS)
  case object GreaterEquals extends Operator(GREATER_EQUALS)
  case object LeftShift extends Operator(LEFT_SHIFT)
  case object SignedRightShift extends Operator(SIGNED_RIGHT_SHIFT)
  case object UnsignedRightShift extends Operator(UNSIGNED_RIGHT_SHIFT)
  case object Plus extends Operator(PLUS)
  case object Minus extends Operator(MINUS)
  case object Multiply extends Operator(MULTIPLY)
  case object Divide extends Operator(DIVIDE)
  case object Remainder extends Operator(REMAINDER)


  def apply(operator: Operator, left: Expression, right: Expression): Binary =
    operator(left, right)

  def unapply(b: Binary): Some[(
    Operator,
    Expression,
    Expression
  )] =
    Some((
      Operator(b.getOperator),
      b.getLeft,
      b.getRight
    ))


  private
  val lookup = mutable.Map[OperatorEnum, Operator]()

  object Operator {
    // Ensure all cases are initialized
    Or
    And
    BinaryOr
    BinaryAnd
    Xor
    Equals
    NotEquals
    Less
    Greater
    LessEquals
    GreaterEquals
    LeftShift
    SignedRightShift
    UnsignedRightShift
    Plus
    Minus
    Multiply
    Divide
    Remainder

    def apply(enum: OperatorEnum): Operator =
      lookup(enum)

    def unapply(o: Operator): Some[OperatorEnum] =
      Some(o.enum)
  }

  sealed abstract class Operator(private val enum: OperatorEnum) {
    lookup += enum -> this

    def apply(left: Expression, right: Expression): Binary =
      new Binary(left, right, enum)

    def unapply(b: Binary): Option[(Expression, Expression)] =
      if (b.getOperator == enum) {
        Some((b.getLeft, b.getRight))
      } else {
        None
      }
  }
}
