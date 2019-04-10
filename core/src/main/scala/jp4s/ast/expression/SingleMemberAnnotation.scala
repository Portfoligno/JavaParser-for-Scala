package jp4s.ast
package expression

import nejc4s.alias.Nejl

object SingleMemberAnnotation {
  def apply(
    name: Nejl[Identifier],
    memberValue: Expression
  ): SingleMemberAnnotation =
    new SingleMemberAnnotation(
      nameNode(name),
      memberValue
    )

  def unapply(a: SingleMemberAnnotation): Some[(
    Nejl[Identifier],
    Expression
  )] =
    Some((
      identifiers(a.getName),
      a.getMemberValue
    ))
}
