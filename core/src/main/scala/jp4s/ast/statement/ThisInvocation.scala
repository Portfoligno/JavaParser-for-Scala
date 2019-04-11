package jp4s.ast
package statement

import jp4s.ast.`type`.Type
import jp4s.ast.expression.Expression
import nejc4s.base.{JavaList, Optional}

object ThisInvocation {
  import jp4s.syntax.optional._

  def apply(
    typeArguments: Optional[JavaList[Type]],
    expression: Optional[Expression],
    arguments: JavaList[Expression]
  ): ExplicitConstructorInvocation =
    new ExplicitConstructorInvocation(
      typeArguments.transform(nodeList).orElseNull,
      true,
      expression.orElseNull,
      nodeList(arguments)
    )

  def unapply(i: ExplicitConstructorInvocation): Option[(
    Optional[JavaList[Type]],
    Optional[Expression],
    JavaList[Expression]
  )] =
    if (i.isThis) {
      ExplicitConstructorInvocation.unapply(i)
    } else {
      None
    }
}
