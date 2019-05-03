package jp4s.ast
package expression

import jp4s.ast.`type`.Type
import nejc4s.alias.Nejl
import nejc4s.base.Optional

object ArrayCreation {
  import nejc4s.syntax.optional._

  def apply(
    elementType: Type,
    levels: Nejl[ArrayCreationLevel],
    initializer: Optional[ArrayInitializer]
  ): ArrayCreation =
    new ArrayCreation(
      elementType,
      nodeList(levels),
      initializer.orElseNull
    )

  def unapply(c: ArrayCreation): Some[(
    Type,
    Nejl[ArrayCreationLevel],
    Optional[ArrayInitializer]
  )] =
    Some((c.getElementType, nejl(c.getLevels), c.getInitializer))
}
