package jp4s.ast.data

class IdentifierStringContext(private val sc: StringContext) extends AnyVal {
  // TODO: macro
  def id[A >: Any](expressions: A*): Identifier =
    Identifier.unsafeFromString(sc.s())
}
