package jp4s.ast
package module

import nejc4s.NonEmptyJavaList
import nejc4s.base.JavaList

object Exports {
  def apply(
    name: NonEmptyJavaList[Identifier],
    moduleNames: JavaList[NonEmptyJavaList[Identifier]]
  ): Exports =
    new Exports(
      nameNode(name),
      identifiersAsNameNodes(moduleNames)
    )

  def unapply(e: Exports): Some[(
    NonEmptyJavaList[Identifier],
    JavaList[NonEmptyJavaList[Identifier]]
  )] =
    Some((
      identifiers(e.getName),
      nameNodesAsIdentifiers(e.getModuleNames)
    ))
}
