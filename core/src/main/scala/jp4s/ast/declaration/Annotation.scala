package jp4s.ast
package declaration

import com.github.javaparser.ast.body.AnnotationDeclaration
import jp4s.ast.name.Simple
import jp4s.utility.JavaList

object Annotation {
  def apply(
    modifiers: JavaList[Modifier],
    annotations: JavaList[expression.Annotation],
    name: Simple,
    members: JavaList[Body]
  ): Annotation =
    new AnnotationDeclaration(
      nodes(modifiers),
      nodes(annotations),
      name,
      nodes(members)
    )

  def unapply(a: Annotation): Option[(
    NodeList[Modifier],
    NodeList[expression.Annotation],
    Simple,
    NodeList[Body]
  )] =
    Some((a.getModifiers, a.getAnnotations, a.getName, a.getMembers))
}
