package jp4s.ast
package module

import nejc4s.alias.Nejl
import nejc4s.base.JavaList

object Exports {
  def apply(
    name: Nejl[Identifier],
    moduleNames: JavaList[Nejl[Identifier]]
  ): Exports =
    new Exports(
      nameNode(name),
      identifiersAsNameNodes(moduleNames)
    )

  def unapply(e: Exports): Some[(
    Nejl[Identifier],
    JavaList[Nejl[Identifier]]
  )] =
    Some((
      identifiers(e.getName),
      nameNodesAsIdentifiers(e.getModuleNames)
    ))
}
