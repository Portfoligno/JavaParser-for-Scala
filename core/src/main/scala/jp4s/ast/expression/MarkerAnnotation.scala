package jp4s.ast
package expression

import nejc4s.NonEmptyJavaList

object MarkerAnnotation {
  def apply(name: NonEmptyJavaList[Identifier]): MarkerAnnotation =
    new MarkerAnnotation(nameNode(name))

  def unapply(a: MarkerAnnotation): Some[NonEmptyJavaList[Identifier]] =
    Some(identifiers(a.getName))
}
