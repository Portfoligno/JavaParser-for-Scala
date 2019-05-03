package jp4s.ast
package `type`

import jp4s.ast.expression.Annotation
import nejc4s.base.{JavaList, Optional}

object ClassOrInterfaceType {
  import nejc4s.syntax.optional._

  def apply(
    scope: Optional[ClassOrInterfaceType],
    name: Identifier,
    typeArguments: Optional[JavaList[Type]],
    annotations: JavaList[Annotation]
  ): ClassOrInterfaceType =
    new ClassOrInterfaceType(
      scope.orElseNull,
      simpleNameNode(name),
      typeArguments.transform(nodeList).orElseNull,
      nodeList(annotations)
    )

  def unapply(t: ClassOrInterfaceType): Some[(
    Optional[ClassOrInterfaceType],
    Identifier,
    Optional[JavaList[Type]],
    JavaList[Annotation]
  )] =
    Some((
      t.getScope,
      identifier(t.getName),
      t.getTypeArguments.covary,
      t.getAnnotations
    ))
}
