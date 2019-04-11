package jp4s.ast.statement

import com.github.javaparser.ast.expr.Expression
import nejc4s.base.Optional

object Return {
  import jp4s.syntax.optional._

  def apply(expression: Optional[Expression]): Return =
    new Return(expression.orElseNull)

  def unapply(r: Return): Some[Optional[Expression]] =
    Some(r.getExpression)
}
