package jp4s.ast.statement

import com.github.javaparser.ast.expr.Expression
import nejc4s.base.Optional

object Assert {
  import jp4s.syntax.optional._

  def apply(check: Expression, message: Optional[Expression]): Assert =
    new Assert(check, message.orElseNull)

  def unapply(a: Assert): Some[(Expression, Optional[Expression])] =
    Some((a.getCheck, a.getMessage))
}
