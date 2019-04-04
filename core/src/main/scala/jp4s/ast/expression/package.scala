package jp4s.ast

import com.github.javaparser.ast.expr.AnnotationExpr

package object expression {
  type Annotation = AnnotationExpr

  type SimpleName = com.github.javaparser.ast.expr.SimpleName
}
