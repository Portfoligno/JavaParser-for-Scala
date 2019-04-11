package jp4s.ast.statement

import jp4s.ast.declaration.Parameter

object Catch {
  def apply(parameter: Parameter, body: Block): Catch =
    new Catch(parameter, body)

  def unapply(c: Catch): Some[(Parameter, Block)] =
    Some((c.getParameter, c.getBody))
}
