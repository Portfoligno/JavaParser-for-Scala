package jp4s.ast
package module

import nejc4s.NonEmptyJavaList
import nejc4s.base.JavaList

object Requires {
  def apply(
    modifiers: JavaList[Modifier],
    name: NonEmptyJavaList[Identifier]
  ): Requires =
    new Requires(
      nodeList(modifiers),
      nameNode(name)
    )

  def unapply(r: Requires): Some[(JavaList[Modifier], NonEmptyJavaList[Identifier])] =
    Some((
      r.getModifiers,
      identifiers(r.getName)
    ))
}
