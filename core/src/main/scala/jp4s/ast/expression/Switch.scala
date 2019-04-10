package jp4s.ast
package expression

import com.github.javaparser.ast.stmt.SwitchEntry
import nejc4s.base.JavaList

object Switch {
  def apply(selector: Expression, entries: JavaList[SwitchEntry]): Switch =
    new Switch(selector, nodeList(entries))

  def unapply(s: Switch): Some[(
    Expression,
    JavaList[SwitchEntry]
  )] =
    Some((s.getSelector, s.getEntries))
}
