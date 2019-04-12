package jp4s.ast.`type`

object VoidType {
  def apply(): VoidType =
    new VoidType()

  def unapply(t: VoidType): Boolean =
    true
}
