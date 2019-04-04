package jp4s.ast.declaration

import jp4s.ast.statement.Block

object Initializer {
  def apply(isStatic: Boolean, body: Block): Initializer =
    new Initializer(isStatic, body)

  def unapply(i: Initializer): Some[(Boolean, Block)] =
    Some((i.isStatic, i.getBody))
}
