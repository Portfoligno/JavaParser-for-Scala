package jp4s.ast
package `type`

import nejc4s.alias.Nejl

object IntersectionType {
  def apply(
    elements: Nejl[ReferenceType]
  ): IntersectionType =
    new IntersectionType(
      nodeList(elements)
    )

  def unapply(t: IntersectionType): Some[Nejl[ReferenceType]] =
    Some(nejl(t.getElements))
}
