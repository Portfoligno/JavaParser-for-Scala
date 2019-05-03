package jp4s.ast
package statement

import jp4s.ast.expression.Expression
import nejc4s.base.{JavaList, Optional}

object For {
  import nejc4s.syntax.optional._

  def apply(
    initialization: JavaList[Expression],
    compare: Optional[Expression],
    update: JavaList[Expression],
    body: Statement
  ): For =
    new For(
      nodeList(initialization),
      compare.orElseNull,
      nodeList(update),
      body
    )

  def unapply(f: For): Some[(
    JavaList[Expression],
    Optional[Expression],
    JavaList[Expression],
    Statement
  )] =
    Some((
      f.getInitialization,
      f.getCompare,
      f.getUpdate,
      f.getBody
    ))
}
