package jp4s.ast

import com.github.javaparser.ast.{Node, NodeList}
import com.github.javaparser.ast.body.{AnnotationDeclaration, BodyDeclaration}
import jp4s.utility.JavaList

package object declaration {
  type Annotation = AnnotationDeclaration

  type Body = BodyDeclaration[_ <: BodyDeclaration[_]]


  private[declaration]
  def nodes[A <: Node](javaList: JavaList[A]): NodeList[A] =
    javaList match {
      case nodeList: NodeList[A] =>
        nodeList

      case _ =>
        new NodeList(javaList)
    }
}
