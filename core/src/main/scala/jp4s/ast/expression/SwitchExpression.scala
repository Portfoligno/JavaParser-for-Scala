package jp4s.ast
package expression

import jp4s.ast.statement.Case
import nejc4s.base.JavaList

object SwitchExpression {
  def apply(
    selector: Expression,
    entries: JavaList[Case]
  ): SwitchExpression =
    new SwitchExpression(
      selector,
      nodeList(entries)
    )

  def unapply(s: SwitchExpression): Some[(
    Expression,
    JavaList[Case]
  )] =
    Switch.unapply(s)
}
