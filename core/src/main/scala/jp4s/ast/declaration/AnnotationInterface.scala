package jp4s.ast
package declaration

import jp4s.ast.data.Identifier
import jp4s.ast.expression.Annotation
import jp4s.utility.JavaList

object AnnotationInterface {
  def apply(
    modifiers: JavaList[Modifier],
    annotations: JavaList[Annotation],
    name: Identifier,
    members: JavaList[Body]
  ): AnnotationInterface =
    new AnnotationInterface(
      nodeList(modifiers),
      nodeList(annotations),
      SimpleName(name),
      nodeList(members)
    )

  def unapply(a: AnnotationInterface): Some[(
    JavaList[Modifier],
    JavaList[Annotation],
    Identifier,
    JavaList[Body]
  )] =
    Some((a.getModifiers, a.getAnnotations, identifier(a.getName), a.getMembers))
}
