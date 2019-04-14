package jp4s.ast
package `type`

import com.github.javaparser.ast.`type`.ArrayType.Origin
import com.github.javaparser.ast.`type`.ArrayType.Origin._
import jp4s.ast.expression.Annotation
import nejc4s.base.JavaList

import scala.collection.mutable

object ArrayType {
  type VarianceEnum = Origin

  case object OnType extends Variance(TYPE)
  case object OnName extends Variance(NAME)


  def apply(
    variance: ArrayType.Variance,
    componentType: Type,
    annotations: JavaList[Annotation]
  ): ArrayType =
    variance(componentType, annotations)

  def unapply(t: ArrayType): Some[(
    ArrayType.Variance,
    Type,
    JavaList[Annotation]
  )] =
    Some((
      Variance(t.getOrigin),
      t.getComponentType,
      t.getAnnotations
    ))


  private
  val lookup = mutable.Map[VarianceEnum, Variance]()

  object Variance {
    // Ensure all cases are initialized
    OnType
    OnName

    def apply(enum: VarianceEnum): Variance =
      lookup(enum)

    def unapply(v: Variance): Some[VarianceEnum] =
      Some(v.enum)
  }

  sealed abstract class Variance(private val enum: VarianceEnum) {
    lookup += enum -> this

    def apply(
      componentType: Type,
      annotations: JavaList[Annotation]
    ): ArrayType =
      new ArrayType(
        componentType,
        enum,
        nodeList(annotations)
      )

    def unapply(t: ArrayType): Option[(
      Type,
      JavaList[Annotation]
    )] =
      if (t.getOrigin == enum) {
        Some((
          t.getComponentType,
          t.getAnnotations
        ))
      } else {
        None
      }
  }
}
