package jp4s.ast
package statement

import jp4s.ast.`type`.Type
import jp4s.ast.expression.Expression
import nejc4s.base.{JavaList, Optional}

object SuperInvocation {
  import jp4s.syntax.optional._

  def apply(
    typeArguments: Optional[JavaList[Type]],
    expression: Optional[Expression],
    arguments: JavaList[Expression]
  ): SpecialConstructorInvocation =
    new SpecialConstructorInvocation(
      typeArguments.transform(nodeList).orElseNull,
      false,
      expression.orElseNull,
      nodeList(arguments)
    )

  def unapply(i: SpecialConstructorInvocation): Option[(
    Optional[JavaList[Type]],
    Optional[Expression],
    JavaList[Expression]
  )] =
    if (!i.isThis) {
      Some((
        i.getTypeArguments.covary,
        i.getExpression,
        i.getArguments
      ))
    } else {
      None
    }
}
