package jp4s.ast.comment

object Javadoc {
  def apply(content: String): Javadoc =
    new Javadoc(content)

  def unapply(c: Javadoc): Some[String] =
    Some(c.getContent)
}
