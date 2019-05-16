package jp4s.ast
package expression

import nejc4s.NonEmptyJavaList

object SingleMemberAnnotation {
  def apply(
    name: NonEmptyJavaList[Identifier],
    memberValue: Expression
  ): SingleMemberAnnotation =
    new SingleMemberAnnotation(
      nameNode(name),
      memberValue
    )

  def unapply(a: SingleMemberAnnotation): Some[(
    NonEmptyJavaList[Identifier],
    Expression
  )] =
    Some((
      identifiers(a.getName),
      a.getMemberValue
    ))
}
