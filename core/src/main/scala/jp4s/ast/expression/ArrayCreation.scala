package jp4s.ast
package expression

import jp4s.ast.`type`.Type
import nejc4s.base.{JavaList, Optional}

object ArrayCreation {
  import jp4s.syntax.optional._

  def apply(
    elementType: Type,
    levels: JavaList[ArrayCreationLevel],
    initializer: Optional[ArrayInitializer]
  ): ArrayCreation =
    new ArrayCreation(elementType, nodeList(levels), initializer.orElseNull)

  def unapply(c: ArrayCreation): Some[(Type, JavaList[ArrayCreationLevel], Optional[ArrayInitializer])] =
    Some((c.getElementType, c.getLevels, c.getInitializer))
}
