package jp4s.ast
package module

import nejc4s.alias.Nejl

object Uses {
  def apply(name: Nejl[Identifier]): Uses =
    new Uses(nameNode(name))

  def unapply(u: Uses): Some[Nejl[Identifier]] =
    Some(identifiers(u.getName))
}
