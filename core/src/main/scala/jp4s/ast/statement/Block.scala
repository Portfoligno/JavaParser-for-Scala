package jp4s.ast
package statement

object Block {
  import scala.collection.JavaConverters._

  def apply(statements: Statement*): Block =
    new Block(nodeList(statements.asJava))

  def unapplySeq(b: Block): Some[Seq[Statement]] =
    Some(b.getStatements.asScala)
}
