package jp4s.ast
package `type`

import jp4s.ast.expression.Annotation
import nejc4s.base.JavaList

object TypeParameter {
  def apply(
    name: Identifier,
    typeBound: JavaList[ClassOrInterfaceType],
    annotations: JavaList[Annotation]
  ): TypeParameter =
    new TypeParameter(simpleNameNode(name), nodeList(typeBound), nodeList(annotations))

  def unapply(p: TypeParameter): Some[(Identifier, JavaList[ClassOrInterfaceType], JavaList[Annotation])] =
    Some((identifier(p.getName), p.getTypeBound, p.getAnnotations))
}
