package jp4s.ast
package statement

import jp4s.ast.expression.Expression
import nejc4s.base.JavaList

object SwitchStatement {
  def apply(
    selector: Expression,
    entries: JavaList[Case]
  ): SwitchStatement =
    new SwitchStatement(
      selector,
      nodeList(entries)
    )

  def unapply(s: SwitchStatement): Some[(
    Expression,
    JavaList[Case]
  )] =
    Some((
      s.getSelector,
      s.getEntries
    ))
}
