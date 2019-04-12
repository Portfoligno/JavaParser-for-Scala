package jp4s.ast.`type`

import jp4s.ast.expression.Annotation
import nejc4s.base.JavaList

object ReferenceType {
  def unapply(t: ReferenceType): Some[JavaList[Annotation]] =
    Type.unapply(t)
}
