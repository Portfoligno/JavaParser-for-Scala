package jp4s.ast
package declaration

import jp4s.ast.`type`.{ClassOrInterfaceType, TypeParameter}
import jp4s.ast.expression.{Annotation, SimpleName}
import jp4s.utility.JavaList

object ClassOrInterface {
  def apply(
    modifiers: JavaList[Modifier],
    annotations: JavaList[Annotation],
    isInterface: Boolean,
    name: Identifier,
    typeParameters: JavaList[TypeParameter],
    extendedTypes: JavaList[ClassOrInterfaceType],
    implementedTypes: JavaList[ClassOrInterfaceType],
    members: JavaList[Body]
  ): ClassOrInterface =
    new ClassOrInterface(
      nodeList(modifiers),
      nodeList(annotations),
      isInterface,
      SimpleName(name),
      nodeList(typeParameters),
      nodeList(extendedTypes),
      nodeList(implementedTypes),
      nodeList(members)
    )

  def unapply(c: ClassOrInterface): Some[(
    JavaList[Modifier],
    JavaList[Annotation],
    Boolean,
    Identifier,
    JavaList[TypeParameter],
    JavaList[ClassOrInterfaceType],
    JavaList[ClassOrInterfaceType],
    JavaList[Body]
  )] =
    Some((
      c.getModifiers,
      c.getAnnotations,
      c.isInterface,
      identifier(c.getName),
      c.getTypeParameters,
      c.getExtendedTypes,
      c.getImplementedTypes,
      c.getMembers
    ))
}
