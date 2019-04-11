package jp4s.ast.statement

import jp4s.ast.`type`.Type
import jp4s.ast.expression.Expression
import nejc4s.base.{JavaList, Optional}

object SpecialConstructorInvocation {
  import jp4s.syntax.optional._

  def unapply(i: SpecialConstructorInvocation): Some[(
    Optional[JavaList[Type]],
    Optional[Expression],
    JavaList[Expression]
  )] =
    Some((
      i.getTypeArguments.covary,
      i.getExpression,
      i.getArguments
    ))
}
