package jp4s.ast
package statement

object Label {
  def apply(label: Identifier, statement: Statement): Label =
    new Label(simpleNameNode(label), statement)

  def unapply(l: Label): Some[(Identifier, Statement)] =
    Some((identifier(l.getLabel), l.getStatement))
}
