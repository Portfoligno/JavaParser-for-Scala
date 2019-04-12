package jp4s.ast
package `type`

import com.github.javaparser.ast.`type`.PrimitiveType.Primitive
import com.github.javaparser.ast.`type`.PrimitiveType.Primitive._
import jp4s.ast.expression.Annotation
import nejc4s.base.JavaList

object PrimitiveType {
  type Type = Primitive

  object Boolean extends Factory(BOOLEAN)
  object Char extends Factory(CHAR)
  object Byte extends Factory(BYTE)
  object Short extends Factory(SHORT)
  object Int extends Factory(INT)
  object Long extends Factory(LONG)
  object Float extends Factory(FLOAT)
  object Double extends Factory(DOUBLE)


  def unapply(t: PrimitiveType): Some[JavaList[Annotation]] =
    Some(t.getAnnotations)


  sealed class Factory(val `type`: Type) {
    def apply(`type`: Type, annotations: JavaList[Annotation]): PrimitiveType =
      new PrimitiveType(`type`, nodeList(annotations))

    def unapply(t: PrimitiveType): Option[JavaList[Annotation]] =
      if (t.getType == `type`) {
        PrimitiveType.unapply(t)
      } else {
        None
      }
  }
}
