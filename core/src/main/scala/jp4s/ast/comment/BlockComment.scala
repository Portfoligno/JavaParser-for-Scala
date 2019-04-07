package jp4s.ast.comment

object BlockComment {
  def apply(content: String): BlockComment =
    new BlockComment(content)

  def unapply(c: BlockComment): Some[String] =
    Some(c.getContent)
}
