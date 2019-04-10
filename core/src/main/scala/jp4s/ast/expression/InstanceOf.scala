package jp4s.ast.expression

import jp4s.ast.`type`.ReferenceType

object InstanceOf {
  def apply(expression: Expression, `type`: ReferenceType): InstanceOf =
    new InstanceOf(expression, `type`)

  def unapply(i: InstanceOf): Some[(Expression, ReferenceType)] =
    Some((i.getExpression, i.getType))
}
