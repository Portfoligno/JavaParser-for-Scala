package jp4s.ast
package expression

object Annotation {
  def unapply(a: Annotation): Some[NameNode] =
    Some(a.getName)
}
