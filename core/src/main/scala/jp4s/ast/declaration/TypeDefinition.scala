package jp4s.ast
package declaration

import jp4s.ast.expression.Annotation
import nejc4s.base.JavaList

object TypeDefinition {
  def unapply(c: TypeDefinition): Some[(
    JavaList[Modifier],
    JavaList[Annotation],
    Identifier,
    JavaList[Body]
  )] =
    Some((c.getModifiers, c.getAnnotations, identifier(c.getName), c.getMembers))
}