package jp4s.ast
package declaration

import jp4s.ast.name.Simple
import jp4s.utility.JavaList

object Annotation {
  def apply(
    modifiers: JavaList[Modifier],
    annotations: JavaList[expression.Annotation],
    name: Identifier,
    members: JavaList[Body]
  ): Annotation =
    new Annotation(
      nodes(modifiers),
      nodes(annotations),
      Simple(name),
      nodes(members)
    )

  def unapply(a: Annotation): Some[(
    NodeList[Modifier],
    NodeList[expression.Annotation],
    Identifier,
    NodeList[Body]
  )] =
    Some((a.getModifiers, a.getAnnotations, Simple.identifier(a.getName), a.getMembers))
}
