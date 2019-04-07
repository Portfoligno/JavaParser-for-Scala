package jp4s.ast
package declaration

import jp4s.ast.`type`.{ClassOrInterfaceType, TypeParameter}
import jp4s.ast.expression.Annotation
import nejc4s.base.JavaList

object Class {
  def apply(
    modifiers: JavaList[Modifier],
    annotations: JavaList[Annotation],
    name: Identifier,
    typeParameters: JavaList[TypeParameter],
    extendedTypes: JavaList[ClassOrInterfaceType],
    implementedTypes: JavaList[ClassOrInterfaceType],
    members: JavaList[Body]
  ): ClassOrInterface =
    new ClassOrInterface(
      nodeList(modifiers),
      nodeList(annotations),
      false,
      SimpleName(name),
      nodeList(typeParameters),
      nodeList(extendedTypes),
      nodeList(implementedTypes),
      nodeList(members)
    )

  def unapply(c: ClassOrInterface): Option[(
    JavaList[Modifier],
    JavaList[Annotation],
    Identifier,
    JavaList[TypeParameter],
    JavaList[ClassOrInterfaceType],
    JavaList[ClassOrInterfaceType],
    JavaList[Body]
  )] =
    if (c.isInterface) {
      None
    } else {
      Some((
        c.getModifiers,
        c.getAnnotations,
        identifier(c.getName),
        c.getTypeParameters,
        c.getExtendedTypes,
        c.getImplementedTypes,
        c.getMembers
      ))
    }
}
