package jp4s

import com.github.javaparser.ast.NodeList
import nejc4s.base.{JavaList, Optional}

package object ast {
  type ArrayCreationLevel = com.github.javaparser.ast.ArrayCreationLevel
  type Modifier = com.github.javaparser.ast.Modifier
  type NameNode = com.github.javaparser.ast.expr.Name
  type Node = com.github.javaparser.ast.Node
  type SimpleNameNode = com.github.javaparser.ast.expr.SimpleName


  type Identifier <: String with Identifier.Tag

  object Identifier {
    private[ast] trait Tag extends Any

    private
    def isIdentifier(s: String): Boolean =
      s.nonEmpty &&
        Character.isJavaIdentifierStart(s.head) &&
        s.tail.forall(Character.isJavaIdentifierPart)

    def fromString(s: String): Option[Identifier] =
      if (isIdentifier(s)) {
        Some(s.asInstanceOf[Identifier])
      } else {
        None
      }

    def unsafeFromString(s: String): Identifier =
      if (isIdentifier(s)) {
        s.asInstanceOf[Identifier]
      } else {
        throw new IllegalArgumentException(String.valueOf(s))
      }
  }



  private[ast]
  def identifier(s: SimpleNameNode): Identifier =
    Identifier.unsafeFromString(s.getIdentifier)

  private[ast]
  def simpleName(identifier: Identifier): SimpleNameNode =
    SimpleNameNode(identifier)

  private[ast]
  def nodeList[A <: Node](javaList: JavaList[A]): NodeList[A] =
    javaList match {
      case nodeList: NodeList[A] =>
        nodeList

      case _ =>
        new NodeList(javaList)
    }


  private[ast]
  object SimpleNameNode {
    def apply(identifier: Identifier): SimpleNameNode =
      new SimpleNameNode(identifier)

    def unapply(s: SimpleNameNode): Some[Identifier] =
      Some(identifier(s))
  }

  private[ast]
  object NameNode {
    import jp4s.syntax.optional._

    def apply(qualifier: Optional[NameNode], identifier: Identifier): NameNode =
      new NameNode(qualifier.orElseNull, identifier)

    def unapply(n: NameNode): Some[(Optional[NameNode], Identifier)] =
      Some((n.getQualifier, Identifier.unsafeFromString(n.getIdentifier)))
  }
}
