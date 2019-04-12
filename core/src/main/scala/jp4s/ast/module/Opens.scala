package jp4s.ast
package module

import nejc4s.alias.Nejl
import nejc4s.base.JavaList

object Opens {
  def apply(
    name: Nejl[Identifier],
    moduleNames: JavaList[Nejl[Identifier]]
  ): Opens =
    new Opens(
      nameNode(name),
      identifiersAsNameNodes(moduleNames)
    )

  def unapply(e: Opens): Some[(
    Nejl[Identifier],
    JavaList[Nejl[Identifier]]
  )] =
    Some((
      identifiers(e.getName),
      nameNodesAsIdentifiers(e.getModuleNames)
    ))
}
