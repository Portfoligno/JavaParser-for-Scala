package jp4s.ast.declaration

import com.github.javaparser.ast.Modifier
import com.github.javaparser.ast.body.AnnotationDeclaration
import com.github.javaparser.ast.expr.{AnnotationExpr, SimpleName}
import jp4s.utility.JavaList

object Annotation {
  def apply(
    modifiers: JavaList[Modifier],
    annotations: JavaList[AnnotationExpr],
    name: SimpleName,
    members: JavaList[Body]
  ): Annotation =
    new AnnotationDeclaration(
      nodes(modifiers),
      nodes(annotations),
      name,
      nodes(members)
    )
}
