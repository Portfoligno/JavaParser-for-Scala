package jp4s.ast
package declaration

import com.github.javaparser.ast.NodeList
import jp4s.ast.`type`.{ClassOrInterfaceType, TypeParameter}
import jp4s.ast.expression.Annotation
import nejc4s.base.JavaList

private[declaration]
trait InterfaceFactory {
  def apply(
    modifiers: JavaList[Modifier],
    annotations: JavaList[Annotation],
    name: Identifier,
    typeParameters: JavaList[TypeParameter],
    extendedTypes: JavaList[ClassOrInterfaceType],
    members: JavaList[Body]
  ): Interface =
    Interface.unsafeFromClassOrInterface(new ClassOrInterface(
      nodeList(modifiers),
      nodeList(annotations),
      true,
      simpleNameNode(name),
      nodeList(typeParameters),
      nodeList(extendedTypes),
      new NodeList(),
      nodeList(members)
    ))

  def unapply(c: ClassOrInterface): Option[(
    JavaList[Modifier],
    JavaList[Annotation],
    Identifier,
    JavaList[TypeParameter],
    JavaList[ClassOrInterfaceType],
    JavaList[Body]
  )] =
    if (!c.isInterface) {
      None
    } else {
      require(c.getImplementedTypes.isEmpty)

      Some((
        c.getModifiers,
        c.getAnnotations,
        identifier(c.getName),
        c.getTypeParameters,
        c.getExtendedTypes,
        c.getMembers
      ))
    }
}
