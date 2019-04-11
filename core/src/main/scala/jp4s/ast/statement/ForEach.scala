package jp4s.ast.statement

import jp4s.ast.expression.{Expression, Variables}

object ForEach {
  def apply(
    variable: Variables,
    iterable: Expression,
    body: Statement
  ): ForEach =
    new ForEach(
      variable,
      iterable,
      body
    )

  def unapply(f: ForEach): Some[(Variables, Expression, Statement)] =
    Some((f.getVariable, f.getIterable, f.getBody))
}
