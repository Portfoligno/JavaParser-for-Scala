package jp4s.ast
package declaration

import java.util.Optional

import com.github.javaparser.ast.expr.Expression
import jp4s.ast.name.Simple
import jp4s.utility.JavaList

object AnnotationMember {
  def apply(
    modifiers: JavaList[Modifier],
    annotations: JavaList[expression.Annotation],
    `type`: Type,
    name: Simple,
    defaultValue: Optional[Expression]
  ): AnnotationMember =
    new AnnotationMember(
      nodes(modifiers),
      nodes(annotations),
      `type`,
      name,
      defaultValue.orElse(null)
    )

  def unapply(m: AnnotationMember): Option[(
    NodeList[Modifier],
    NodeList[expression.Annotation],
    Type,
    Simple,
    Optional[Expression])
  ] =
    Some((m.getModifiers, m.getAnnotations, m.getType, m.getName, m.getDefaultValue))
}
