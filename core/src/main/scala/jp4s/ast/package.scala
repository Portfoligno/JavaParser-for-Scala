package jp4s

import com.github.javaparser.ast.{Node, NodeList}
import jp4s.utility.JavaList

package object ast {
  type Modifier = com.github.javaparser.ast.Modifier

  private[ast]
  def nodes[A <: Node](javaList: JavaList[A]): NodeList[A] =
    javaList match {
      case nodeList: NodeList[A] =>
        nodeList

      case _ =>
        new NodeList(javaList)
    }
}
