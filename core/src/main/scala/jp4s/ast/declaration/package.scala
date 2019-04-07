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
}
