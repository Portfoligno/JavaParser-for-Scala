package jp4s.ast
package expression

import nejc4s.alias.Nejl
import nejc4s.base.JavaList

object NormalAnnotation {
  def apply(
    name: Nejl[Identifier],
    pairs: JavaList[MemberValuePair]
  ): NormalAnnotation =
    new NormalAnnotation(
      nameNode(name),
      nodeList(pairs)
    )

  def unapply(a: NormalAnnotation): Some[(
    Nejl[Identifier],
    JavaList[MemberValuePair]
  )] =
    Some((
      identifiers(a.getName),
      a.getPairs
    ))
}
