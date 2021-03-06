package jp4s.ast
package expression

import nejc4s.base.{JavaList, Optional}

object ArrayCreationLevel {
  import jp4s.syntax.optional._

  def apply(
    dimension: Optional[Expression],
    annotations: JavaList[Annotation]
  ): ArrayCreationLevel =
    new ArrayCreationLevel(
      dimension.orElseNull,
      nodeList(annotations)
    )

  def unapply(l: ArrayCreationLevel): Some[(
    Optional[Expression],
    JavaList[Annotation]
  )] =
    Some((
      l.getDimension,
      l.getAnnotations
    ))
}
