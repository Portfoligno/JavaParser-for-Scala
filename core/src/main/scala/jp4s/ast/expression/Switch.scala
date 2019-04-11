package jp4s.ast.expression

import jp4s.ast.statement.Case
import nejc4s.base.JavaList

object Switch {
  def unapply(s: Switch): Some[(
    Expression,
    JavaList[Case]
  )] =
    Some((
      s.getSelector,
      s.getEntries
    ))
}
