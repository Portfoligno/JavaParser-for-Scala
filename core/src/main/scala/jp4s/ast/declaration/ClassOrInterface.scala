package jp4s.ast
package declaration

import jp4s.ast.`type`.{ClassOrInterfaceType, TypeParameter}
import jp4s.ast.expression.Annotation
import jp4s.utility.JavaList

object ClassOrInterface {
  def unapply(c: ClassOrInterface): Some[(
    JavaList[Modifier],
    JavaList[Annotation],
    Identifier,
    JavaList[TypeParameter],
    JavaList[ClassOrInterfaceType],
    JavaList[ClassOrInterfaceType],
    JavaList[Body]
  )] =
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
