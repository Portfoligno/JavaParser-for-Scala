package jp4s.ast
package declaration

import jp4s.ast.data.Identifier
import jp4s.ast.expression.Annotation
import nejc4s.base.{JavaList, Optional}

object AnnotationMember {
  def apply(
    modifiers: JavaList[Modifier],
    annotations: JavaList[Annotation],
    `type`: Type,
    name: Identifier,
    defaultValue: Optional[Expression]
  ): AnnotationMember =
    new AnnotationMember(
      nodeList(modifiers),
      nodeList(annotations),
      `type`,
      SimpleName(name),
      defaultValue.orElse(null)
    )

  def unapply(m: AnnotationMember): Some[(
    JavaList[Modifier],
    JavaList[Annotation],
    Type,
    Identifier,
    Optional[Expression]
  )] =
    Some((m.getModifiers, m.getAnnotations, m.getType, identifier(m.getName), m.getDefaultValue))
}
