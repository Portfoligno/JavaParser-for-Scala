package jp4s.ast

import com.github.javaparser.ast.expr.{AnnotationExpr, ArrayAccessExpr}

package object expression {
  type Annotation = AnnotationExpr
  type ArrayAccess = ArrayAccessExpr

  type Expression = com.github.javaparser.ast.expr.Expression
}
