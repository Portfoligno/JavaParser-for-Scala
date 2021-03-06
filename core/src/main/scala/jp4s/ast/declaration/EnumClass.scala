package jp4s.ast
package declaration

import jp4s.ast.`type`.ClassOrInterfaceType
import jp4s.ast.expression.Annotation
import nejc4s.base.JavaList

object EnumClass {
  def apply(
    modifiers: JavaList[Modifier],
    annotations: JavaList[Annotation],
    name: Identifier,
    implementedTypes: JavaList[ClassOrInterfaceType],
    entries: JavaList[Enum],
    members: JavaList[Member]
  ): EnumClass =
    new EnumClass(
      nodeList(modifiers),
      nodeList(annotations),
      simpleNameNode(name),
      nodeList(implementedTypes),
      nodeList(entries),
      nodeList(members)
    )

  def unapply(e: EnumClass): Some[(
    JavaList[Modifier],
    JavaList[Annotation],
    Identifier,
    JavaList[ClassOrInterfaceType],
    JavaList[Enum],
    JavaList[Member]
  )] =
    Some((
      e.getModifiers,
      e.getAnnotations,
      identifier(e.getName),
      e.getImplementedTypes,
      e.getEntries,
      e.getMembers
    ))
}
