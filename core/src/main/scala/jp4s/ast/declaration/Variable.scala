package jp4s.ast
package declaration

import com.github.javaparser.ast.NodeList
import com.github.javaparser.ast.body.VariableDeclarator
import jp4s.ast.data.Identifier
import jp4s.utility._

import scala.collection.JavaConverters._
import scala.collection.convert.ImplicitConversionsToScala._

sealed trait Variable

object Variable {
  case class Parsed(v: VariableDeclarator) extends Variable

  case class Raw(name: Identifier, initializer: Optional[Expression]) extends Variable


  def apply(name: Identifier, initializer: Optional[Expression]): Variable.Raw =
    Raw(name, initializer)

  def unapply(variable: Variable): Option[(Identifier, Optional[Expression])] =
    variable match {
      case Parsed(v) =>
        Some((identifier(v.getName), v.getInitializer))

      case r: Raw =>
        Raw.unapply(r)
    }


  private[declaration]
  def javaList(variables: NodeList[VariableDeclarator]): JavaList[Variable] =
    new MappedJavaList(variables)(Parsed)

  private[declaration]
  def nodeList(`type`: Type, variables: JavaList[Variable]): NodeList[VariableDeclarator] = {
    def convertElements: NodeList[VariableDeclarator] =
      new NodeList(
        variables
          .map {
            case Parsed(v) =>
              new VariableDeclarator(`type`.clone(), v.getName, v.getInitializer.orElseNull)

            case Raw(name, initializer) =>
              new VariableDeclarator(`type`.clone(), SimpleName(name), initializer.orElseNull)
          }
          .asJava
      )

    variables match {
      case mapped: MappedJavaList[_, Variable] =>
        mapped.delegate match {
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
