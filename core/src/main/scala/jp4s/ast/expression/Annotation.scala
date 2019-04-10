package jp4s.ast
package expression

import nejc4s.alias.Nejl

object Annotation {
  def unapply(a: Annotation): Some[Nejl[Identifier]] =
    Some(identifiers(a.getName))
}
