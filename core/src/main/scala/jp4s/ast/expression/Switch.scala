package jp4s.ast
package expression

import jp4s.ast.statement.Case
import nejc4s.base.JavaList

object Switch {
  def apply(selector: Expression, entries: JavaList[Case]): Switch =
    new Switch(selector, nodeList(entries))

  def unapply(s: Switch): Some[(
    Expression,
    JavaList[Case]
  )] =
    Some((s.getSelector, s.getEntries))
}
