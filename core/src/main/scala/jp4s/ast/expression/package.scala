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
  type Enclosed = EnclosedExpr
  type Expression = com.github.javaparser.ast.expr.Expression
  type FieldAccess = FieldAccessExpr
  type FloatingPointLiteral = DoubleLiteralExpr
  type InstanceOf = InstanceOfExpr
  type IntegerLiteral = IntegerLiteralExpr
  type Lambda = LambdaExpr
  type Literal = LiteralExpr
  type LiteralByString = LiteralStringValueExpr
  type LongLiteral = LongLiteralExpr
  type MarkerAnnotation = MarkerAnnotationExpr
  type MemberValuePair = com.github.javaparser.ast.expr.MemberValuePair
  type MethodCall = MethodCallExpr
  type MethodReference = MethodReferenceExpr
  type Name = NameExpr
  type NormalAnnotation = NormalAnnotationExpr
  type NullLiteral = NullLiteralExpr
  type ObjectCreation = ObjectCreationExpr
  type SingleMemberAnnotation = SingleMemberAnnotationExpr
  type StringLiteral = StringLiteralExpr
  type SuperAccess = SuperExpr
}
