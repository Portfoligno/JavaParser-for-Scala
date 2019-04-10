package jp4s.ast

import com.github.javaparser.ast.expr._

package object expression {
  type Annotation = AnnotationExpr
  type ArrayAccess = ArrayAccessExpr
  type ArrayCreation = ArrayCreationExpr
  type ArrayInitializer = ArrayInitializerExpr
  type Assign = AssignExpr
  type Binary = BinaryExpr
  type BooleanLiteral = BooleanLiteralExpr
  type Cast = CastExpr
  type CharLiteral = CharLiteralExpr
  type ClassAccess = ClassExpr
  type Conditional = ConditionalExpr

  type Expression = com.github.javaparser.ast.expr.Expression
}
