package jp4s.ast

import com.github.javaparser.ast.expr.AnnotationExpr

package object expression {
  type Annotation = AnnotationExpr

  type Expression = com.github.javaparser.ast.expr.Expression
}
