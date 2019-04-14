package jp4s.ast
package `type`

object UnionType {
  def apply(
    element: ReferenceType, otherElements: ReferenceType*
  ): UnionType =
    new UnionType(
      cons(element, otherElements)
    )

  def unapplySeq(t: UnionType): Some[(ReferenceType, Seq[ReferenceType])] =
    Some(uncons(t.getElements))
}
