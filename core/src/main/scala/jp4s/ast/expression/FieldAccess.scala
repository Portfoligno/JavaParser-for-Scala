package jp4s.ast
package expression

import nejc4s.base.Absent

object FieldAccess {
  import nejc4s.syntax.optional._

  def apply(
    scope: Expression,
    name: Identifier
  ): FieldAccess =
    new FieldAccess(
      scope,
      Absent[Null]().orElseNull,
      simpleNameNode(name)
    )

  def unapply(a: FieldAccess): Some[(
    Expression,
    Identifier
  )] = {
    require(!a.getTypeArguments.isPresent)

    Some((
      a.getScope,
      identifier(a.getName)
    ))
  }
}
