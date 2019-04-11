package jp4s.ast.statement

import jp4s.ast.declaration.Parameter

object CatchClause {
  def apply(parameter: Parameter, body: Block): CatchClause =
    new CatchClause(parameter, body)

  def unapply(c: CatchClause): Some[(Parameter, Block)] =
    Some((c.getParameter, c.getBody))
}
