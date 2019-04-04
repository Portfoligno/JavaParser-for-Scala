package jp4s

import eu.timepit.refined.api.{Refined, RefinedTypeOps}
import eu.timepit.refined.boolean.And
import eu.timepit.refined.char.{Letter, LetterOrDigit}
import eu.timepit.refined.collection.{Head, Tail}
import jp4s.utility.JavaList

import scala.language.implicitConversions

package object ast {
  type Identifier = String Refined (Head[Letter] And Tail[LetterOrDigit])
  object Identifier extends RefinedTypeOps[Identifier, String]


  type Expression = com.github.javaparser.ast.expr.Expression

  type Modifier = com.github.javaparser.ast.Modifier

  type Type = com.github.javaparser.ast.`type`.Type

  type Node = com.github.javaparser.ast.Node

  type NodeList[A <: Node] = com.github.javaparser.ast.NodeList[A]



  class IdentifierStringContext(private val sc: StringContext) extends AnyVal {
    // TODO: macro
    def id[A >: Any](expressions: A*): Identifier =
      Identifier.unsafeFrom(sc.s())
  }

  implicit def toSimpleStringContext(sc: StringContext): IdentifierStringContext =
    new IdentifierStringContext(sc)


  private[ast]
  def nodes[A <: Node](javaList: JavaList[A]): NodeList[A] =
    javaList match {
      case nodeList: NodeList[A] =>
        nodeList

      case _ =>
        new NodeList(javaList)
    }
}
