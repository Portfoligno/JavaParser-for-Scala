package jp4s.ast.comment

object LineComment {
  def apply(content: String): LineComment =
    new LineComment(content)

  def unapply(c: LineComment): Some[String] =
    Some(c.getContent)
}
