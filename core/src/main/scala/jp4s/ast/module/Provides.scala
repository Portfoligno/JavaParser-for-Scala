package jp4s.ast
package module

import nejc4s.NonEmptyJavaList
import nejc4s.base.JavaList

object Provides {
  def apply(
    name: NonEmptyJavaList[Identifier],
    `with`: JavaList[NonEmptyJavaList[Identifier]]
  ): Provides =
    new Provides(
      nameNode(name),
      identifiersAsNameNodes(`with`)
    )

  def unapply(e: Provides): Some[(
    NonEmptyJavaList[Identifier],
    JavaList[NonEmptyJavaList[Identifier]]
  )] =
    Some((
      identifiers(e.getName),
      nameNodesAsIdentifiers(e.getWith)
    ))
}
