package jp4s.ast
package module

import nejc4s.NonEmptyJavaList
import nejc4s.base.JavaList

object Opens {
  def apply(
    name: NonEmptyJavaList[Identifier],
    moduleNames: JavaList[NonEmptyJavaList[Identifier]]
  ): Opens =
    new Opens(
      nameNode(name),
      identifiersAsNameNodes(moduleNames)
    )

  def unapply(e: Opens): Some[(
    NonEmptyJavaList[Identifier],
    JavaList[NonEmptyJavaList[Identifier]]
  )] =
    Some((
      identifiers(e.getName),
      nameNodesAsIdentifiers(e.getModuleNames)
    ))
}
