package jp4s.ast
package statement

import jp4s.ast.`type`.Type
import jp4s.ast.expression.Expression
import nejc4s.base.{JavaList, Optional}

case object ThisInvocation extends SpecialConstructorInvocation.Variance(true)

case object SuperInvocation extends SpecialConstructorInvocation.Variance(false)

object SpecialConstructorInvocation {
  import nejc4s.syntax.optional._

  def apply(
    variance: SpecialConstructorInvocation.Variance,
    typeArguments: Optional[JavaList[Type]],
    expression: Optional[Expression],
    arguments: JavaList[Expression]
  ): SpecialConstructorInvocation =
    variance(typeArguments, expression, arguments)

  def unapply(i: SpecialConstructorInvocation): Some[(
    SpecialConstructorInvocation.Variance,
    Optional[JavaList[Type]],
    Optional[Expression],
    JavaList[Expression]
  )] =
    Some((
      Variance(i.isThis),
      i.getTypeArguments.covary,
      i.getExpression,
      i.getArguments
    ))


  object Variance {
    def apply(isThis: Boolean): Variance =
      if (isThis) ThisInvocation else SuperInvocation

    def unapply(v: Variance): Some[Boolean] =
      Some(v.isThis)
  }

  sealed abstract class Variance(private val isThis: Boolean) {
    import nejc4s.syntax.optional._

    def apply(
      typeArguments: Optional[JavaList[Type]],
      expression: Optional[Expression],
      arguments: JavaList[Expression]
    ): SpecialConstructorInvocation =
      new SpecialConstructorInvocation(
        typeArguments.transform(nodeList).orElseNull,
        isThis,
        expression.orElseNull,
        nodeList(arguments)
      )

    def unapply(i: SpecialConstructorInvocation): Option[(
      Optional[JavaList[Type]],
      Optional[Expression],
      JavaList[Expression]
    )] =
      if (i.isThis ^ isThis) {
        None
      } else {
        Some((
          i.getTypeArguments.covary,
          i.getExpression,
          i.getArguments
        ))
      }
  }
}
