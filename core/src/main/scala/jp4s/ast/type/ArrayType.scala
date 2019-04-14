package jp4s.ast
package `type`

import com.github.javaparser.ast.`type`.ArrayType.Origin
import com.github.javaparser.ast.`type`.ArrayType.Origin._
import jp4s.ast.expression.Annotation
import nejc4s.base.JavaList

object ArrayType {
  object OnType extends Variance(TYPE)
  object OnName extends Variance(NAME)

  def unapply(t: ArrayType): Some[(
    Type,
    JavaList[Annotation]
  )] =
    Some((
      t.getComponentType,
      t.getAnnotations
    ))


  sealed abstract class Variance(origin: Origin) {
    def apply(
      componentType: Type,
      annotations: JavaList[Annotation]
    ): ArrayType =
      new ArrayType(
        componentType,
        origin,
        nodeList(annotations)
      )

    def unapply(t: ArrayType): Option[(
      Type,
      JavaList[Annotation]
    )] =
      if (t.getOrigin == origin) {
        ArrayType.unapply(t)
      } else {
        None
      }
  }
}
