package jp4s.ast

import com.github.javaparser.ast.body._
import com.github.javaparser.ast.{ImportDeclaration, PackageDeclaration}

package object declaration {
  type AnnotationInterface = AnnotationDeclaration
  type AnnotationMember = AnnotationMemberDeclaration
  type Body = BodyDeclaration[_ <: BodyDeclaration[_]]
  type Callable = CallableDeclaration[_ <: CallableDeclaration[_]]
  type ClassOrInterface = ClassOrInterfaceDeclaration
  type Constructor = ConstructorDeclaration
  type Enum = EnumConstantDeclaration
  type EnumClass = EnumDeclaration
  type Fields = FieldDeclaration
  type Import = ImportDeclaration
  type Initializer = InitializerDeclaration
  type Method = MethodDeclaration
  type Package = PackageDeclaration
  type Parameter = com.github.javaparser.ast.body.Parameter
  type ReceiverParameter = com.github.javaparser.ast.body.ReceiverParameter
  type TypeDefinition = TypeDeclaration[_ <: TypeDeclaration[_]]


  object Package extends PackageFactory


  object ClassOrInterface {
    sealed trait Variance
  }


  type Class <: ClassOrInterfaceDeclaration with Class.Tag

  case object Class extends ClassFactory with ClassOrInterface.Variance {
    private[ast] trait Tag extends Any

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

  case object Interface extends InterfaceFactory with ClassOrInterface.Variance {
    private[ast] trait Tag extends Any

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
