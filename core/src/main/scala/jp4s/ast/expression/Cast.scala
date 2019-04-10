package jp4s.ast.expression

import jp4s.ast.`type`.Type

object Cast {
  def apply(`type`: Type, expression: Expression): Cast =
    new Cast(`type`, expression)

  def unapply(c: Cast): Some[(Type, Expression)] =
    Some((c.getType, c.getExpression))
}
