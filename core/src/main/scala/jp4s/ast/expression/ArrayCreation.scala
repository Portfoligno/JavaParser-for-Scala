package jp4s.ast
package expression

import jp4s.ast.`type`.Type
import nejc4s.NonEmptyJavaList
import nejc4s.base.Optional

object ArrayCreation {
  import nejc4s.syntax.optional._

  def apply(
    elementType: Type,
    levels: NonEmptyJavaList[ArrayCreationLevel],
    initializer: Optional[ArrayInitializer]
  ): ArrayCreation =
    new ArrayCreation(
      elementType,
      nodeList(levels),
      initializer.orElseNull
    )

  def unapply(c: ArrayCreation): Some[(
    Type,
    NonEmptyJavaList[ArrayCreationLevel],
    Optional[ArrayInitializer]
  )] =
    Some((c.getElementType, nejl(c.getLevels), c.getInitializer))
}
