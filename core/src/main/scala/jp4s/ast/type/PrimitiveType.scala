package jp4s.ast
package `type`

import com.github.javaparser.ast.`type`.PrimitiveType.Primitive
import com.github.javaparser.ast.`type`.PrimitiveType.Primitive._
import jp4s.ast.expression.Annotation
import nejc4s.base.JavaList

import scala.collection.mutable

object PrimitiveType {
  type VarianceEnum = Primitive

  case object Boolean extends Variance(BOOLEAN)
  case object Char extends Variance(CHAR)
  case object Byte extends Variance(BYTE)
  case object Short extends Variance(SHORT)
  case object Int extends Variance(INT)
  case object Long extends Variance(LONG)
  case object Float extends Variance(FLOAT)
  case object Double extends Variance(DOUBLE)


  def apply(variance: Variance, annotations: JavaList[Annotation]): PrimitiveType =
    variance(annotations)

  def unapply(t: PrimitiveType): Some[(PrimitiveType.Variance, JavaList[Annotation])] =
    Some((Variance(t.getType), t.getAnnotations))


  private
  val lookup = mutable.Map[VarianceEnum, Variance]()

  object Variance {
    // Ensure all cases are initialized
    Boolean
    Char
    Byte
    Short
    Int
    Long
    Float
    Double

    def apply(enum: VarianceEnum): Variance =
      lookup(enum)

    def unapply(v: Variance): Some[VarianceEnum] =
      Some(v.enum)
  }

  sealed abstract class Variance(private val enum: VarianceEnum) {
    lookup += enum -> this

    def apply(annotations: JavaList[Annotation]): PrimitiveType =
      new PrimitiveType(enum, nodeList(annotations))

    def unapply(t: PrimitiveType): Option[JavaList[Annotation]] =
      if (t.getType == enum) {
        Some(t.getAnnotations)
      } else {
        None
      }
  }
}
