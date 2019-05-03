package jp4s.ast.expression

import nejc4s.base.Optional

object ThisAccess {
  import nejc4s.syntax.optional._

  def apply(classExpr: Optional[Expression]): ThisAccess =
    new ThisAccess(classExpr.orElseNull)

  def unapply(a: ThisAccess): Some[Optional[Expression]] =
    Some(a.getClassExpr)
}
