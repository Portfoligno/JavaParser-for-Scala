package jp4s.ast.statement

object Noop {
  def apply(): Noop =
    new Noop()

  def unapply(n: Noop): Boolean =
    true
}
