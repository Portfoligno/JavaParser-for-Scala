package jp4s.ast
package declaration

import com.github.javaparser.ast.NodeList
import com.github.javaparser.ast.body.VariableDeclarator
import jp4s.ast.`type`.ArrayType
import jp4s.ast.data.Identifier
import jp4s.ast.expression.Annotation
import nejc4s.alias.Nejl
import nejc4s.base.{JavaCollection, JavaList, Optional}

import scala.annotation.tailrec

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



  case class Pure(
    arrayDimensions: JavaList[JavaList[Annotation]],
    name: Identifier,
    initializer: Optional[Expression]
  ) extends Variable

  case class ByNode(v: VariableDeclarator) extends Variable {
    override
    def arrayDimensions: JavaList[JavaList[Annotation]] =
      NestingOnName.unapply(v.getType).get._1

    override
    def name: Identifier =
      identifier(v.getName)

    override
    def initializer: Optional[Expression] =
      v.getInitializer
  }



  import scala.collection.JavaConverters._
  import scala.collection.convert.ImplicitConversionsToScala._

  private[declaration]
  def nejl(variables: NodeList[VariableDeclarator]): Nejl[Variable] =
    new VariableNejlProxy(variables)

  private[declaration]
  def nodeList(`type`: Type, variables: Nejl[Variable]): NodeList[VariableDeclarator] =
    variables match {
      case p: VariableNejlProxy if p
        .source
        .view
        .map(_.getType)
        .forall {
          case NestingOnName(_, `type`) => true
          case _ => false
        }
      =>
        p.source

      case _ =>
        import jp4s.syntax.optional._

        new NodeList(
          variables
            .view
            .map(v =>
              new VariableDeclarator(
                NestingOnName(v.arrayDimensions, `type`),
                SimpleName(v.name),
                v.initializer.orElseNull
              )
            )
            .asJava
        )
    }


  private
  class VariableNejlProxy(val source: NodeList[VariableDeclarator])
    extends Nejl.UnsafeProxy[Variable]
      with JavaList[Variable]
      with JavaCollection[Variable] {
    protected
    override def delegate: JavaList[Variable] =
      (source.view.map(ByNode): Seq[Variable]).asJava
  }


  private
  object NestingOnName {
    @tailrec
    def apply(dimensions: Seq[JavaList[Annotation]], baseType: Type): Type =
      dimensions match {
        case Seq(annotations, remainingDimensions @ _*) =>
          NestingOnName(remainingDimensions, ArrayType(
            baseType,
            ArrayType.Origin.Name,
            annotations)
          )

        case _ =>
          baseType
      }

    def unapply(nestedType: Type): Some[(JavaList[JavaList[Annotation]], Type)] = {
      @tailrec
      def unwrap(dimensions: List[JavaList[Annotation]], currentType: Type): (JavaList[JavaList[Annotation]], Type) =
        currentType match {
          case ArrayType(componentType, ArrayType.Origin.Name, annotations) =>
            unwrap(annotations :: dimensions, componentType)

          case _ =>
            dimensions.asJava -> currentType
        }

      Some(unwrap(Nil, nestedType))
    }
  }
}
