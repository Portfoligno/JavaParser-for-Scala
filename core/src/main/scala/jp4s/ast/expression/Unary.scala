package jp4s.ast.expression

import com.github.javaparser.ast.expr.UnaryExpr
import com.github.javaparser.ast.expr.UnaryExpr.Operator._

import scala.collection.mutable

object Unary {
  type OperatorEnum = UnaryExpr.Operator

  case object Plus extends Operator(PLUS)
  case object Minus extends Operator(MINUS)
  case object PrefixIncrement extends Operator(PREFIX_INCREMENT)
  case object PrefixDecrement extends Operator(PREFIX_DECREMENT)
  case object LogicalComplement extends Operator(LOGICAL_COMPLEMENT)
  case object BitwiseComplement extends Operator(BITWISE_COMPLEMENT)
  case object PostfixIncrement extends Operator(POSTFIX_INCREMENT)
  case object PostfixDecrement extends Operator(POSTFIX_DECREMENT)


  def apply(operator: Operator, expression: Expression): Unary =
    operator(expression)

  def unapply(u: Unary): Some[(
    Operator,
      Expression
    )] =
    Some((
      Operator(u.getOperator),
      u.getExpression
    ))


  private
  val lookup = mutable.Map[OperatorEnum, Operator]()

  object Operator {
    // Ensure all cases are initialized
    Plus
    Minus
    PrefixIncrement
    PrefixDecrement
    LogicalComplement
    BitwiseComplement
    PostfixIncrement
    PostfixDecrement

    def apply(enum: OperatorEnum): Operator =
      lookup(enum)

    def unapply(o: Operator): Some[OperatorEnum] =
      Some(o.enum)
  }

  sealed abstract class Operator(private val enum: OperatorEnum) {
    lookup += enum -> this

    def apply(expression: Expression): Unary =
      new Unary(expression, enum)

    def unapply(u: Unary): Option[Expression] =
      if (u.getOperator == enum) {
        Some(u.getExpression)
      } else {
        None
      }
  }
}
