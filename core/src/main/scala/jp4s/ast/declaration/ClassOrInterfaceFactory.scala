package jp4s.ast
package declaration

import jp4s.ast.`type`.{ClassOrInterfaceType, TypeParameter}
import jp4s.ast.expression.Annotation
import nejc4s.base.JavaList

private[declaration]
trait ClassOrInterfaceFactory {
  import jp4s.syntax.optional._

  def unapply(c: ClassOrInterface): Some[(
    ClassOrInterface.Variance,
    JavaList[Modifier],
    JavaList[Annotation],
    Identifier,
    JavaList[TypeParameter],
    (JavaList[ClassOrInterfaceType], JavaList[ClassOrInterfaceType]),
    JavaList[Body]
  )] =
    Some(c match {
      case Class(modifiers, annotations, name, typeParameters, (extended, implemented), members) =>
        (Class, modifiers, annotations, name, typeParameters, (extended.toJavaList, implemented), members)

      case Interface(modifiers, annotations, name, typeParameters, extended, members) =>
        (Interface, modifiers, annotations, name, typeParameters, (extended, JavaList()), members)
    })
}
