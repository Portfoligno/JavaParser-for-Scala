package jp4s.ast
package declaration

import jp4s.ast.expression.SimpleName
import jp4s.utility.JavaList

object Annotation {
  def apply(
    modifiers: JavaList[Modifier],
    annotations: JavaList[expression.Annotation],
    name: Identifier,
    members: JavaList[Body]
  ): Annotation =
    new Annotation(
      nodeList(modifiers),
      nodeList(annotations),
      SimpleName(name),
      nodeList(members)
    )

  def unapply(a: Annotation): Some[(
    NodeList[Modifier],
    NodeList[expression.Annotation],
    Identifier,
    NodeList[Body]
  )] =
    Some((a.getModifiers, a.getAnnotations, identifier(a.getName), a.getMembers))
}
