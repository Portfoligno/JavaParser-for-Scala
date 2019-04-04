package jp4s

import jp4s.utility.JavaList

package object ast {
  type Modifier = com.github.javaparser.ast.Modifier

  type Type = com.github.javaparser.ast.`type`.Type

  type Node = com.github.javaparser.ast.Node

  type NodeList[A <: Node] = com.github.javaparser.ast.NodeList[A]


  private[ast]
  def nodes[A <: Node](javaList: JavaList[A]): NodeList[A] =
    javaList match {
      case nodeList: NodeList[A] =>
        nodeList

      case _ =>
        new NodeList(javaList)
    }
}
