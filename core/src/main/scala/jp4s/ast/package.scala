package jp4s

import com.github.javaparser.ast.NodeList
import nejc4s.base.JavaList

package object ast {
  type Modifier = com.github.javaparser.ast.Modifier
  type Node = com.github.javaparser.ast.Node
  type SimpleName = com.github.javaparser.ast.expr.SimpleName


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
  def identifier(s: SimpleName): Identifier =
    Identifier.unsafeFromString(s.getIdentifier)

  private[ast]
  def nodeList[A <: Node](javaList: JavaList[A]): NodeList[A] =
    javaList match {
      case nodeList: NodeList[A] =>
        nodeList

      case _ =>
        new NodeList(javaList)
    }
}
