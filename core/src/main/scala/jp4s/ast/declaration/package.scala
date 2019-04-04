package jp4s.ast

import com.github.javaparser.ast.body.{AnnotationDeclaration, AnnotationMemberDeclaration, BodyDeclaration, CallableDeclaration}

package object declaration {
  type Annotation = AnnotationDeclaration
  type AnnotationMember = AnnotationMemberDeclaration
  type Body = BodyDeclaration[_ <: BodyDeclaration[_]]
  type Callable = CallableDeclaration[_ <: CallableDeclaration[_]]
}
