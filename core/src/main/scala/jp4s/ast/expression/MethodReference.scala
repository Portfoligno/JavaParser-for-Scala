package jp4s.ast
package expression

import com.github.javaparser.ast.`type`.Type
import nejc4s.base.{JavaList, Optional}

object MethodReference {
  import jp4s.syntax.optional._

  def apply(
    scope: Expression,
    typeArguments: Optional[JavaList[Type]],
    identifier: Identifier
  ): MethodReference =
    new MethodReference(
      scope,
      typeArguments.transform(nodeList).orElseNull,
      identifier
    )

  def unapply(r: MethodReference): Some[(
    Expression,
    Optional[JavaList[Type]],
    Identifier
  )] =
    Some((
      r.getScope,
      r.getTypeArguments.covary,
      Identifier.unsafeFromString(r.getIdentifier)
    ))
}
