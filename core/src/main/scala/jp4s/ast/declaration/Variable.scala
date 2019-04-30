package jp4s.ast
package declaration

import com.github.javaparser.ast.NodeList
import com.github.javaparser.ast.body.VariableDeclarator
import jp4s.ast.`type`.{ArrayType, Type}
import jp4s.ast.expression.{Annotation, Expression}
import jp4s.extra.ast.`type`.NestedArrayType
import nejc4s.alias.Nejl
import nejc4s.base.{JavaCollection, JavaList, Optional}

sealed trait Variable {
  def arrayDimensions: JavaList[JavaList[Annotation]]

  def name: Identifier

  def initializer: Optional[Expression]
}

object Variable {
  def apply(
    arrayDimensions: JavaList[JavaList[Annotation]],
    name: Identifier,
    initializer: Optional[Expression]
  ): Variable =
    Pure(arrayDimensions, name, initializer)

  def unapply(v: Variable): Some[(
    JavaList[JavaList[Annotation]],
    Identifier,
    Optional[Expression]
  )] =
    Some((v.arrayDimensions, v.name, v.initializer))



  private
  implicit def arrayTypeVariance: ArrayType.Variance =
    ArrayType.OnName

  private
  case class Pure(
    arrayDimensions: JavaList[JavaList[Annotation]],
    name: Identifier,
    initializer: Optional[Expression]
  ) extends Variable

  private
  case class ByNode(v: VariableDeclarator) extends Variable {
    override
    def arrayDimensions: JavaList[JavaList[Annotation]] =
      NestedArrayType.unapply(v.getType).get._2

    override
    def name: Identifier =
      identifier(v.getName)

    override
    def initializer: Optional[Expression] =
      v.getInitializer
  }



  import jp4s.syntax.optional._

  import scala.collection.JavaConverters._
  import scala.collection.convert.ImplicitConversionsToScala._

  private[ast]
  def `type`(variables: NodeList[VariableDeclarator]): Type =
    NestedArrayType.unapply(variables.iterator.next().getType).get._1

  private[ast]
  def nejl(variables: NodeList[VariableDeclarator]): Nejl[Variable] =
    VariableNejlWrapper(variables)

  private[ast]
  def nodeList(`type`: Type, variables: Nejl[Variable]): NodeList[VariableDeclarator] =
    variables match {
      case VariableNejlWrapper(source) if source
        .view
        .map(_.getType)
        .forall {
          case NestedArrayType(`type`, _) => true
          case _ => false
        }
      =>
        source

      case _ =>
        new NodeList(
          variables
            .view
            .map(v =>
              new VariableDeclarator(
                NestedArrayType(`type`, v.arrayDimensions),
                simpleNameNode(v.name),
                v.initializer.orElseNull
              )
            )
            .asJava
        )
    }


  private
  case class VariableNejlWrapper(source: NodeList[VariableDeclarator])
    extends Nejl.UnsafeProxy[Variable]
      with JavaList[Variable]
      with JavaCollection[Variable] {
    protected
    override def delegate: JavaList[Variable] =
      (source.view.map(ByNode): Seq[Variable]).asJava
  }
}
