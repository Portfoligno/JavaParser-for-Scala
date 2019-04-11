package jp4s.ast.statement

import jp4s.ast.expression.Expression
import nejc4s.base.Optional

object Break {
  import jp4s.syntax.optional._

  def apply(value: Optional[Expression]): Break =
    new Break(value.orElseNull)

  def unapply(b: Break): Some[Optional[Expression]] =
    Some(b.getValue)
}
