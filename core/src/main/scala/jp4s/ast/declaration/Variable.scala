package jp4s
package ast
package declaration

import com.github.javaparser.ast.NodeList
import com.github.javaparser.ast.body.VariableDeclarator
import jp4s.ast.data.Identifier
import nejc4s.alias.Nejl
import nejc4s.base.{JavaCollection, JavaList, Optional}

import scala.collection.JavaConverters._
import scala.collection.convert.ImplicitConversionsToScala._

sealed trait Variable {
  def name: Identifier

  def initializer: Optional[Expression]
}

object Variable {
  case class Node(v: VariableDeclarator) extends Variable {
    override
    def name: Identifier =
      identifier(v.getName)

    override
    def initializer: Optional[Expression] =
      v.getInitializer
  }

  case class Pure(name: Identifier, initializer: Optional[Expression]) extends Variable


  def apply(name: Identifier, initializer: Optional[Expression]): Variable =
    Pure(name, initializer)

  def unapply(v: Variable): Some[(Identifier, Optional[Expression])] =
    Some(v.name -> v.initializer)


  private
  class Proxy[A, B](val source: JavaList[A])(mapper: A => B)
    extends Nejl.UnsafeProxy[B]
      with JavaList[B]
      with JavaCollection[B] {
    protected
    override def delegate: JavaList[B] = source.view.map(mapper).asJava
  }

  private[declaration]
  def nejl(variables: NodeList[VariableDeclarator]): Nejl[Variable] =
    new Proxy(variables)(Node)

  private[declaration]
  def nodeList(`type`: Type, variables: Nejl[Variable]): NodeList[VariableDeclarator] = {
    def convertElements: NodeList[VariableDeclarator] =
      new NodeList(
        variables
          .map {
            case Node(v) =>
              new VariableDeclarator(`type`.clone(), v.getName, v.getInitializer.orElseNull)

            case Pure(name, initializer) =>
              new VariableDeclarator(`type`.clone(), SimpleName(name), initializer.orElseNull)
          }
          .asJava
      )

    variables match {
      case p: Proxy[_, Variable] =>
        p.source match {
          case nodes: NodeList[_] if nodes.forall {
            case v: VariableDeclarator if v.getType == `type` =>
              true

            case _ =>
              false
          } =>
            nodes.asInstanceOf[NodeList[VariableDeclarator]]

          case _ =>
            convertElements
        }

      case _ =>
        convertElements
    }
  }
}
