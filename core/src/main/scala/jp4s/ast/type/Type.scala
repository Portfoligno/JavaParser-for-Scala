package jp4s.ast.`type`

import jp4s.ast.expression.Annotation
import nejc4s.base.JavaList

object Type {
  def unapply(t: Type): Some[JavaList[Annotation]] =
    Some(t.getAnnotations)
}
