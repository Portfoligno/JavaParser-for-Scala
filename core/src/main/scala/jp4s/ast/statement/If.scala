package jp4s.ast.statement

import jp4s.ast.expression.Expression
import nejc4s.base.Optional

object If {
  import jp4s.syntax.optional._

  def apply(condition: Expression, thenStmt: Statement, elseStmt: Optional[Statement]): If =
    new If(condition, thenStmt, elseStmt.orElseNull)

  def unapply(i: If): Some[(Expression, Statement, Optional[Statement])] =
    Some((i.getCondition, i.getThenStmt, i.getElseStmt))
}
