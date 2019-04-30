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
  type Variable = VariableDeclarator


  object Package extends PackageFactory


  object ClassOrInterface {
    object Variance {
      def apply(isInterface: Boolean): Variance =
        if (isInterface) Interface else Class

      def unapply(v: Variance): Some[Boolean] =
        Some(v.isInterface)
    }

    sealed abstract class Variance(private val isInterface: Boolean) {
      private[declaration]
      type Repr <: ClassOrInterface


      def fromClassOrInterface(c: ClassOrInterface): Option[Repr] =
        if (c.isInterface ^ isInterface) {
          None
        } else {
          Some(c.asInstanceOf[Repr])
        }

      def unsafeFromClassOrInterface(c: ClassOrInterface): Repr =
        if (c.isInterface ^ isInterface) {
          throw new IllegalArgumentException(String.valueOf(c))
        } else {
          c.asInstanceOf[Repr]
        }
    }
  }


  type Class <: ClassOrInterfaceDeclaration with Class.Tag

  case object Class extends ClassOrInterface.Variance(false) with ClassFactory {
    private[ast] trait Tag extends Any

    private[declaration] override type Repr = Class
  }


  type Interface <: ClassOrInterfaceDeclaration with Interface.Tag

  case object Interface extends ClassOrInterface.Variance(true) with InterfaceFactory {
    private[ast] trait Tag extends Any

    private[declaration] override type Repr = Interface
  }
}
