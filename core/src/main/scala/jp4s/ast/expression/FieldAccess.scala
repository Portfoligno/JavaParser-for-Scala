package jp4s.ast
package expression

import jp4s.ast.`type`.Type
import nejc4s.base.{JavaList, Optional}

object FieldAccess {
  import jp4s.syntax.optional._

  def apply(
    scope: Expression,
    typeArguments: Optional[JavaList[Type]],
    name: Identifier
  ): FieldAccess =
    new FieldAccess(
      scope,
      typeArguments.transform(nodeList).orElseNull,
      simpleNameNode(name)
    )

  def unapply(a: FieldAccess): Some[(
    Expression,
    Optional[JavaList[Type]],
    Identifier
  )] =
    Some((
      a.getScope,
      a.getTypeArguments.covary,
      identifier(a.getName)
    ))
}
