package jp4s.ast.name

import eu.timepit.refined.types.string.NonEmptyString

class SimpleStringContext(private val sc: StringContext) extends AnyVal {
  // TODO: Convert to macro
  def simple[A >: Any](expressions: A*): Simple =
    Simple(NonEmptyString.unsafeFrom(sc.s(expressions: _*)))
}
