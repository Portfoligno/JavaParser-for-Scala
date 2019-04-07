package jp4s.syntax

import jp4s.ast.Identifier

trait IdentifierSyntax {
  implicit def toIdStringContext(sc: StringContext): IdStringContext =
    new IdStringContext(sc)
}

class IdStringContext(private val sc: StringContext) extends AnyVal {
  def id[A >: Any](expressions: A*): Identifier =
    Identifier.unsafeFromString(sc.s()) // TODO: macro
}
