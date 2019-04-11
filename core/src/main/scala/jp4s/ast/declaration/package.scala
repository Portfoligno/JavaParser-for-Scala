package jp4s.ast

import com.github.javaparser.ast.body._

package object declaration {
  type AnnotationInterface = AnnotationDeclaration
  type AnnotationMember = AnnotationMemberDeclaration
  type Body = BodyDeclaration[_ <: BodyDeclaration[_]]
  type Callable = CallableDeclaration[_ <: CallableDeclaration[_]]
  type ClassOrInterface = ClassOrInterfaceDeclaration
  type Constructor = ConstructorDeclaration
  type EnumClass = EnumDeclaration
  type EnumConstant = EnumConstantDeclaration
  type Fields = FieldDeclaration
  type Initializer = InitializerDeclaration
  type Method = MethodDeclaration
  type Parameter = com.github.javaparser.ast.body.Parameter
  type ReceiverParameter = com.github.javaparser.ast.body.ReceiverParameter
  type TypeBody = TypeDeclaration[_ <: TypeDeclaration[_]]



  type Class <: ClassOrInterfaceDeclaration with Class.Tag

  object Class extends ClassFactory {
    private[base] trait Tag extends Any

    def fromClassOrInterface(c: ClassOrInterface): Option[Class] =
      if (!c.isInterface) {
        Some(c.asInstanceOf[Class])
      } else {
        None
      }

    def unsafeFromClassOrInterface(c: ClassOrInterface): Class =
      if (!c.isInterface) {
        c.asInstanceOf[Class]
      } else {
        throw new IllegalArgumentException(String.valueOf(c))
      }
  }


  type Interface <: ClassOrInterfaceDeclaration with Interface.Tag

  object Interface extends InterfaceFactory {
    private[base] trait Tag extends Any

    def fromClassOrInterface(c: ClassOrInterface): Option[Interface] =
      if (c.isInterface) {
        Some(c.asInstanceOf[Interface])
      } else {
        None
      }

    def unsafeFromClassOrInterface(c: ClassOrInterface): Interface =
      if (c.isInterface) {
        c.asInstanceOf[Interface]
      } else {
        throw new IllegalArgumentException(String.valueOf(c))
      }
  }
}
