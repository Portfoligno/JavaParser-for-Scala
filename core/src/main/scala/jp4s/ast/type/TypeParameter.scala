package jp4s.ast
package `type`

import jp4s.ast.expression.{Annotation, SimpleName}
import jp4s.utility.JavaList

object TypeParameter {
  def apply(
    name: Identifier,
    typeBound: JavaList[ClassOrInterfaceType],
    annotations: JavaList[Annotation]
  ): TypeParameter =
    new TypeParameter(SimpleName(name), nodeList(typeBound), nodeList(annotations))

  def unapply(p: TypeParameter): Option[(Identifier, JavaList[ClassOrInterfaceType], JavaList[Annotation])] =
    Some((identifier(p.getName), p.getTypeBound, p.getAnnotations))
}
