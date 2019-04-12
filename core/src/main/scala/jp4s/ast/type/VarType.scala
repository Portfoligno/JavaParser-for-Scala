package jp4s.ast.`type`

object VarType {
  def apply(): VarType =
    new VarType()

  def unapply(t: VarType): Boolean =
    true
}
