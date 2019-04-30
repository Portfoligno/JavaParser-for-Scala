package jp4s.extra.ast.expression

import jp4s.ast.Node
import jp4s.ast.declaration.Variable
import jp4s.ast.expression.Assign._
import jp4s.ast.expression._
import nejc4s.base.Present

object := {
  def unapply(n: Node): Option[(
    Expression,
    Expression
  )] =
    n match {
      case Variable(_, lhs, Present(rhs)) =>
        Some(Name(lhs) -> rhs)

      case Assign(operator, lhs, rhs) =>
        val binaryOperator = operator match {
          case Plain => None
          case Plus => Some(Binary.Plus)
          case Minus => Some(Binary.Minus)
          case Multiply => Some(Binary.Multiply)
          case Divide => Some(Binary.Divide)
          case BinaryAnd => Some(Binary.BinaryAnd)
          case BinaryOr => Some(Binary.BinaryOr)
          case Xor => Some(Binary.Xor)
          case Remainder => Some(Binary.Remainder)
          case LeftShift => Some(Binary.LeftShift)
          case SignedRightShift => Some(Binary.SignedRightShift)
          case UnsignedRightShift => Some(Binary.UnsignedRightShift)
        }

        Some(lhs ->
          binaryOperator.fold(
            rhs
          )(
            // TODO: Remove redundant parentheses
            _(Enclosed(lhs), Enclosed(rhs))
          )
        )

      case _ =>
        None
    }
}
