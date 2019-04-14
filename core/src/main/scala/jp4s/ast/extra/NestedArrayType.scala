package jp4s.ast.extra

import jp4s.ast.`type`.{ArrayType, Type}
import jp4s.ast.expression.Annotation
import nejc4s.base.JavaList

import scala.annotation.tailrec

object NestedArrayType {
  import scala.collection.JavaConverters._

  def apply(
    `type`: Type, dimensions: JavaList[JavaList[Annotation]]
  )(
    implicit factory: ArrayType.BracketPlace
  ): Type = {
    @tailrec
    def wrap(
      current: Type,
      dimensions: Seq[JavaList[Annotation]]
    ): Type =
      dimensions match {
        case Seq(annotations, remainingDimensions @ _*) =>
          wrap(factory(current, annotations), remainingDimensions)

        case _ =>
          current
      }

    wrap(`type`, dimensions.asScala)
  }

  def unapply(`type`: Type)(
    implicit factory: ArrayType.BracketPlace
  ): Some[(Type, JavaList[JavaList[Annotation]])] = {
    @tailrec
    def unwrap(
      current: Type,
      dimensions: List[JavaList[Annotation]]
    ): (Type, JavaList[JavaList[Annotation]]) =
      current match {
        case factory(componentType, annotations) =>
          unwrap(componentType, annotations :: dimensions)

        case _ =>
          current -> dimensions.asJava
      }

    Some(unwrap(`type`, Nil))
  }
}
