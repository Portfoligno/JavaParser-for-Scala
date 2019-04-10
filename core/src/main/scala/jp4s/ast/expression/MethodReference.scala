package jp4s.ast
package expression

import com.github.javaparser.ast.expr.TypeExpr
import jp4s.ast.`type`.Type
import nejc4s.base.{JavaList, Optional}

object MethodReference {
  import jp4s.syntax.optional._

  def apply(
    scope: Either[Type, Expression],
    typeArguments: Optional[JavaList[Type]],
    identifier: Identifier
  ): MethodReference =
    new MethodReference(
      scope.fold(new TypeExpr(_), identity),
      typeArguments.transform(nodeList).orElseNull,
      identifier
    )

  def unapply(r: MethodReference): Some[(
    Either[Type, Expression],
    Optional[JavaList[Type]],
    Identifier
  )] =
    Some((
      r.getScope match {
        case t: TypeExpr => Left(t.getType)
        case x: _ => Right(x)
      },
      r.getTypeArguments.covary,
      Identifier.unsafeFromString(r.getIdentifier)
    ))
}
