package jp4s.ast
package declaration

import jp4s.ast.expression.SimpleName
import jp4s.utility.{JavaList, Optional}

object AnnotationMember {
  def apply(
    modifiers: JavaList[Modifier],
    annotations: JavaList[expression.Annotation],
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
    NodeList[Modifier],
    NodeList[expression.Annotation],
    Type,
    Identifier,
    Optional[Expression])
  ] =
    Some((m.getModifiers, m.getAnnotations, m.getType, identifier(m.getName), m.getDefaultValue))
}
