package jp4s.ast
package expression

object Annotation {
  def unapply(a: Annotation): Some[Name] =
    Some(a.getName)
}
