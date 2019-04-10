package jp4s.ast
package expression

import jp4s.ast.`type`.{ClassOrInterfaceType, Type}
import jp4s.ast.declaration.Body
import nejc4s.base.{JavaList, Optional}

object ObjectCreation {
  import jp4s.syntax.optional._

  def apply(
    scope: Optional[Expression],
    `type`: ClassOrInterfaceType,
    typeArguments: Optional[JavaList[Type]],
    arguments: JavaList[Expression],
    anonymousClassBody: Optional[JavaList[Body]]
  ): ObjectCreation =
    new ObjectCreation(
      scope.orElseNull,
      `type`,
      typeArguments.transform(nodeList).orElseNull,
      nodeList(arguments),
      anonymousClassBody.transform(nodeList).orElseNull
    )

  def unapply(c: ObjectCreation): Some[(
    Optional[Expression],
    ClassOrInterfaceType,
    Optional[JavaList[Type]],
    JavaList[Expression],
    Optional[JavaList[Body]]
  )] =
    Some((
      c.getScope,
      c.getType,
      c.getTypeArguments.covary,
      c.getArguments,
      c.getAnonymousClassBody.covary
    ))
}
