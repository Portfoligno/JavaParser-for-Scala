package jp4s.ast
package statement

import jp4s.ast.`type`.Type
import jp4s.ast.expression.Expression
import nejc4s.base.{JavaList, Optional}

object ThisCall {
  import jp4s.syntax.optional._

  def apply(
    typeArguments: Optional[JavaList[Type]],
    expression: Optional[Expression],
    arguments: JavaList[Expression]
  ): ExplicitConstructorCall =
    new ExplicitConstructorCall(
      typeArguments.transform(nodeList).orElseNull,
      true,
      expression.orElseNull,
      nodeList(arguments)
    )

  def unapply(i: ExplicitConstructorCall): Option[(
    Optional[JavaList[Type]],
    Optional[Expression],
    JavaList[Expression]
  )] =
    if (i.isThis) {
      ExplicitConstructorCall.unapply(i)
    } else {
      None
    }
}
