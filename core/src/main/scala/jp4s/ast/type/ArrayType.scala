package jp4s.ast
package `type`

import com.github.javaparser.ast.`type`.ArrayType.Origin._
import jp4s.ast.expression.Annotation
import nejc4s.base.JavaList

object ArrayType {
  type Origin = com.github.javaparser.ast.`type`.ArrayType.Origin

  object Origin {
    val Name = NAME
    val Type = TYPE
  }


  def apply(
    componentType: Type,
    origin: ArrayType.Origin,
    annotations: JavaList[Annotation]
  ): ArrayType =
    new ArrayType(
      componentType,
      origin,
      nodeList(annotations)
    )

  def unapply(t: ArrayType): Some[(
    Type,
    ArrayType.Origin,
    JavaList[Annotation]
  )] =
    Some((
      t.getComponentType,
      t.getOrigin,
      t.getAnnotations
    ))
}
