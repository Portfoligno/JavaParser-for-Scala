package jp4s.ast

import com.github.javaparser.ast.body._

package object declaration {
  type AnnotationInterface = AnnotationDeclaration
  type AnnotationMember = AnnotationMemberDeclaration
  type Body = BodyDeclaration[_ <: BodyDeclaration[_]]
  type Callable = CallableDeclaration[_ <: CallableDeclaration[_]]
  type ClassOrInterface = ClassOrInterfaceDeclaration
  type Constructor = ConstructorDeclaration
  type EnumConstant = EnumConstantDeclaration
  type Field = FieldDeclaration

  type Parameter = com.github.javaparser.ast.body.Parameter

  type ReceiverParameter = com.github.javaparser.ast.body.ReceiverParameter
}
