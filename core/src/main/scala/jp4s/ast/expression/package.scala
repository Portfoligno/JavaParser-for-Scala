package jp4s.ast

import com.github.javaparser.ast.expr._

package object expression {
  type Annotation = AnnotationExpr
  type ArrayAccess = ArrayAccessExpr
  type ArrayCreation = ArrayCreationExpr
  type ArrayInitializer = ArrayInitializerExpr
  type Assign = AssignExpr
  type Binary = BinaryExpr

  type Expression = com.github.javaparser.ast.expr.Expression
}
