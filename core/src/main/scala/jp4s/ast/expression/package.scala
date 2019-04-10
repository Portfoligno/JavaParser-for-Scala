package jp4s.ast

import com.github.javaparser.ast.expr.{AnnotationExpr, ArrayAccessExpr, ArrayCreationExpr, ArrayInitializerExpr}

package object expression {
  type Annotation = AnnotationExpr
  type ArrayAccess = ArrayAccessExpr
  type ArrayCreation = ArrayCreationExpr

  type ArrayInitializer = ArrayInitializerExpr

  type Expression = com.github.javaparser.ast.expr.Expression
}
