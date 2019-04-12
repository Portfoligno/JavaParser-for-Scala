package jp4s.ast
package `type`

import nejc4s.alias.Nejl

object UnionType {
  def apply(
    elements: Nejl[ReferenceType]
  ): UnionType =
    new UnionType(
      nodeList(elements)
    )

  def unapply(t: UnionType): Some[Nejl[ReferenceType]] =
    Some(nejl(t.getElements))
}
