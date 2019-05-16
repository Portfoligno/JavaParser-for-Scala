package jp4s.ast
package expression

import nejc4s.NonEmptyJavaList
import nejc4s.base.JavaList

object NormalAnnotation {
  def apply(
    name: NonEmptyJavaList[Identifier],
    pairs: JavaList[MemberValuePair]
  ): NormalAnnotation =
    new NormalAnnotation(
      nameNode(name),
      nodeList(pairs)
    )

  def unapply(a: NormalAnnotation): Some[(
    NonEmptyJavaList[Identifier],
    JavaList[MemberValuePair]
  )] =
    Some((
      identifiers(a.getName),
      a.getPairs
    ))
}
