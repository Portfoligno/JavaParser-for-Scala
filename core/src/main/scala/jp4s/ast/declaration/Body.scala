package jp4s.ast.declaration

import jp4s.ast.expression.Annotation
import nejc4s.base.JavaList

object Body {
  def unapply(b: Body): Some[JavaList[Annotation]] =
    Some(b.getAnnotations)
}
