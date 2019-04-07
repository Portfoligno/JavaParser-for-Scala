package jp4s.ast

import nejc4s.base.Optional

object Name {
  import jp4s.syntax.optional._

  def apply(qualifier: Optional[Name], identifier: Identifier): Name =
    new Name(qualifier.orElseNull, identifier)

  def unapply(n: Name): Some[(Optional[Name], Identifier)] =
    Some((n.getQualifier, Identifier.unsafeFromString(n.getIdentifier)))
}
