package jp4s.ast
package declaration

import com.github.javaparser.ast.body.AnnotationDeclaration
import com.github.javaparser.ast.expr.SimpleName
import jp4s.utility.JavaList

object Annotation {
  def apply(
    modifiers: JavaList[Modifier],
    annotations: JavaList[expression.Annotation],
    name: SimpleName,
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
    SimpleName,
    NodeList[Body]
  )] =
    Some((a.getModifiers, a.getAnnotations, a.getName, a.getMembers))
}
