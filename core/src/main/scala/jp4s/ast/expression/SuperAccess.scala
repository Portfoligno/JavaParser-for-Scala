package jp4s.ast.expression

import java.util.Optional

object SuperAccess {
  import jp4s.syntax.optional._

  def apply(classExpr: Optional[Expression]): SuperAccess =
    new SuperAccess(classExpr.orElseNull)

  def unapply(a: SuperAccess): Some[Optional[Expression]] =
    Some(a.getClassExpr)
}
