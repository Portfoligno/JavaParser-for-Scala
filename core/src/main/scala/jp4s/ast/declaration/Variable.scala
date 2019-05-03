package jp4s.ast
package declaration

import com.github.javaparser.ast.NodeList
import com.github.javaparser.ast.body.VariableDeclarator
import jp4s.ast.`type`.{ArrayDimensions, ArrayType, PrimitiveType, Type}
import jp4s.ast.expression.Expression
import jp4s.extra.ast.`type`.NestedArrayType
import nejc4s.alias.Nejl
import nejc4s.base.{JavaCollection, JavaList, Optional}

object Variable {
  import nejc4s.syntax.optional._
  import jp4s.syntax.variable._

  private
  implicit def arrayTypeVariance: ArrayType.Variance =
    ArrayType.OnName

  def apply(
    arrayDimensions: ArrayDimensions,
    name: Identifier,
    initializer: Optional[Expression]
  ): Variable =
    new Variable(
      NestedArrayType(
        PrimitiveType.Boolean(JavaList()), // Use `boolean` as a placeholder
        arrayDimensions
      ),
      simpleNameNode(name),
      initializer.orElseNull
    )

  def unapply(v: Variable): Some[(
    ArrayDimensions,
    Identifier,
    Optional[Expression]
  )] =
    Some((
      v.arrayDimensions,
      identifier(v.getName),
      v.getInitializer
    ))



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
                v.getName,
                v.getInitializer.orElseNull
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
      source
  }
}
