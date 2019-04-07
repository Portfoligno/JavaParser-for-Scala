package jp4s

import com.github.javaparser.ast.NodeList
import jp4s.ast.data.Identifier
import nejc4s.base.JavaList

package object ast {
  type Modifier = com.github.javaparser.ast.Modifier

  type SimpleName = com.github.javaparser.ast.expr.SimpleName

  type Node = com.github.javaparser.ast.Node


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
