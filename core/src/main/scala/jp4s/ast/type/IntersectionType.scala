package jp4s.ast
package `type`

object IntersectionType {
  def apply(
    element: ReferenceType, otherElements: ReferenceType*
  ): IntersectionType =
    new IntersectionType(
      cons(element, otherElements)
    )

  def unapplySeq(t: IntersectionType): Some[(ReferenceType, Seq[ReferenceType])] =
    Some(uncons(t.getElements))
}
