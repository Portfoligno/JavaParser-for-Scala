package jp4s.ast.`type`

object ImplicitType {
  def apply(): ImplicitType =
    new ImplicitType()

  def unapply(t: ImplicitType): Boolean =
    true
}
