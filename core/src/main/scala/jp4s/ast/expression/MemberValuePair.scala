package jp4s.ast
package expression

object MemberValuePair {
  def apply(name: Identifier, value: Expression): MemberValuePair =
    new MemberValuePair(simpleNameNode(name), value)

  def unapply(p: MemberValuePair): Some[(Identifier, Expression)] =
    Some((identifier(p.getName), p.getValue))
}
