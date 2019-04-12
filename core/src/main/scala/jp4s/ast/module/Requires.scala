package jp4s.ast
package module

import nejc4s.alias.Nejl
import nejc4s.base.JavaList

object Requires {
  def apply(
    modifiers: JavaList[Modifier],
    name: Nejl[Identifier]
  ): Requires =
    new Requires(
      nodeList(modifiers),
      nameNode(name)
    )

  def unapply(r: Requires): Some[(JavaList[Modifier], Nejl[Identifier])] =
    Some((
      r.getModifiers,
      identifiers(r.getName)
    ))
}
