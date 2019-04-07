package jp4s.ast.comment

object Comment {
  def unapply(c: Comment): Some[String] =
    Some(c.getContent)
}
