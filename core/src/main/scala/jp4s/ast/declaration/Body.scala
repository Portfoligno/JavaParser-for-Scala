package jp4s.ast.declaration

import jp4s.ast.expression.Annotation
import jp4s.utility.JavaList

object Body {
  def unapply(b: Body): Some[JavaList[Annotation]] =
    Some(b.getAnnotations)
}
