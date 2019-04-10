package jp4s.ast
package expression

import nejc4s.alias.Nejl

object MarkerAnnotation {
  def apply(name: Nejl[Identifier]): MarkerAnnotation =
    new MarkerAnnotation(nameNode(name))

  def unapply(a: MarkerAnnotation): Some[Nejl[Identifier]] =
    Some(identifiers(a.getName))
}
