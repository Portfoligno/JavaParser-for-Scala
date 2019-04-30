package jp4s.extra.ast.`type`

import jp4s.ast.`type`.{ArrayType, Type}
import jp4s.ast.expression.Annotation
import nejc4s.base.JavaList

import scala.annotation.tailrec

object NestedArrayType {
  import scala.collection.JavaConverters._

  def apply(
    `type`: Type, dimensions: JavaList[JavaList[Annotation]]
  )(
    implicit variance: ArrayType.Variance
  ): Type = {
    @tailrec
    def wrap(
      current: Type,
      dimensions: Seq[JavaList[Annotation]]
    ): Type =
      dimensions match {
        case Seq(annotations, remainingDimensions @ _*) =>
          wrap(variance(current, annotations), remainingDimensions)

        case _ =>
          current
      }

    wrap(`type`, dimensions.asScala)
  }

  def unapply(`type`: Type)(
    implicit variance: ArrayType.Variance
  ): Some[(Type, JavaList[JavaList[Annotation]])] = {
    @tailrec
    def unwrap(
      current: Type,
      dimensions: List[JavaList[Annotation]]
    ): (Type, JavaList[JavaList[Annotation]]) =
      current match {
        case variance(componentType, annotations) =>
          unwrap(componentType, annotations :: dimensions)

        case _ =>
          current -> dimensions.asJava
      }

    Some(unwrap(`type`, Nil))
  }
}
