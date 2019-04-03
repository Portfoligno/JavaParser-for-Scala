package jp4s.ast

import com.github.javaparser.ast.body.{AnnotationDeclaration, BodyDeclaration}

package object declaration {
  type Annotation = AnnotationDeclaration

  type Body = BodyDeclaration[_ <: BodyDeclaration[_]]
}
