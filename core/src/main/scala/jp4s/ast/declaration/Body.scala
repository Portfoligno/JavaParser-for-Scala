package jp4s.ast.declaration

import jp4s.ast.NodeList
import jp4s.ast.expression.Annotation

object Body {
  def unapply(b: Body): Some[NodeList[Annotation]] =
    Some(b.getAnnotations)
}
