package jp4s.ast
package declaration

import com.github.javaparser.ast.NodeList
import jp4s.ast.`type`.{ClassOrInterfaceType, TypeParameter}
import jp4s.ast.expression.Annotation
import nejc4s.base.{Absent, JavaList, Optional, Present}

private[declaration]
trait ClassFactory {
  def apply(
    modifiers: JavaList[Modifier],
    annotations: JavaList[Annotation],
    name: Identifier,
    typeParameters: JavaList[TypeParameter],
    superTypes: (Optional[ClassOrInterfaceType], JavaList[ClassOrInterfaceType]),
    members: JavaList[Member]
  ): Class =
    Class.unsafeFromClassOrInterface(new ClassOrInterface(
      nodeList(modifiers),
      nodeList(annotations),
      false,
      simpleNameNode(name),
      nodeList(typeParameters),
      superTypes._1 match {
        case Present(t) => new NodeList(t)
        case _ => new NodeList()
      },
      nodeList(superTypes._2),
      nodeList(members)
    ))

  def unapply(c: ClassOrInterface): Option[(
    JavaList[Modifier],
    JavaList[Annotation],
    Identifier,
    JavaList[TypeParameter],
    (Optional[ClassOrInterfaceType], JavaList[ClassOrInterfaceType]),
    JavaList[Member]
  )] =
    if (c.isInterface) {
      None
    } else {
      Some((
        c.getModifiers,
        c.getAnnotations,
        identifier(c.getName),
        c.getTypeParameters,
        (
          c.getExtendedTypes match {
            case JavaList() => Absent()
            case JavaList(t) => Present(t)
            case r @ _ => throw new IllegalArgumentException(String.valueOf(r))
          },
          c.getImplementedTypes
        ),
        c.getMembers
      ))
    }
}
