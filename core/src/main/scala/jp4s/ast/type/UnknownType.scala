package jp4s.ast.`type`

object UnknownType {
  def apply(): UnknownType =
    new UnknownType()

  def unapply(t: UnknownType): Boolean =
    true
}
