package jp4s.ast
package declaration

import jp4s.ast.`type`.{ClassOrInterfaceType, TypeParameter}
import jp4s.ast.expression.Annotation
import nejc4s.base.{JavaList, Optional}

private[declaration]
trait ClassOrInterfaceFactory {
  def apply(
    modifiers: JavaList[Modifier],
    annotations: JavaList[Annotation],
    name: Identifier,
    typeParameters: JavaList[TypeParameter],
    superTypes: Either[
      JavaList[ClassOrInterfaceType],
      (Optional[ClassOrInterfaceType], JavaList[ClassOrInterfaceType])
    ],
    members: JavaList[Body]
  ): ClassOrInterface =
    superTypes.fold(
      Interface(modifiers, annotations, name, typeParameters, _, members),
      Class(modifiers, annotations, name, typeParameters, _, members)
    )

  def unapply(c: ClassOrInterface): Some[(
    JavaList[Modifier],
    JavaList[Annotation],
    Identifier,
    JavaList[TypeParameter],
    Either[
      JavaList[ClassOrInterfaceType],
      (Optional[ClassOrInterfaceType], JavaList[ClassOrInterfaceType])
    ],
    JavaList[Body]
  )] =
    Some(c match {
      case Class(modifiers, annotations, name, typeParameters, superTypes, members) =>
        (modifiers, annotations, name, typeParameters, Right(superTypes), members)

      case Interface(modifiers, annotations, name, typeParameters, extendedTypes, members) =>
        (modifiers, annotations, name, typeParameters, Left(extendedTypes), members)
    })
}
