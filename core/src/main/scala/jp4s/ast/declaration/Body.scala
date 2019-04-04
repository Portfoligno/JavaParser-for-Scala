package jp4s.ast.declaration

import jp4s.ast.{NodeList, expression}

object Body {
  def unapply(b: Body): Some[NodeList[expression.Annotation]] =
    Some(b.getAnnotations)
}
