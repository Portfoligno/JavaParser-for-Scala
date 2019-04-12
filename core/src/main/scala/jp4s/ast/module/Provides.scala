package jp4s.ast
package module

import nejc4s.alias.Nejl
import nejc4s.base.JavaList

object Provides {
  def apply(
    name: Nejl[Identifier],
    `with`: JavaList[Nejl[Identifier]]
  ): Provides =
    new Provides(
      nameNode(name),
      identifiersAsNameNodes(`with`)
    )

  def unapply(e: Provides): Some[(
    Nejl[Identifier],
    JavaList[Nejl[Identifier]]
  )] =
    Some((
      identifiers(e.getName),
      nameNodesAsIdentifiers(e.getWith)
    ))
}
