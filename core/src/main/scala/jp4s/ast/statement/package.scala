package jp4s.ast

import com.github.javaparser.ast.stmt._

package object statement {
  type Assert = AssertStmt
  type Block = BlockStmt
  type Break = BreakStmt
  type Catch = com.github.javaparser.ast.stmt.CatchClause
  type Continue = ContinueStmt
  type DeclareLocal = LocalClassDeclarationStmt
  type Do = DoStmt
  type Execution = ExpressionStmt
  type ForEach = ForEachStmt
  type If = IfStmt
  type Label = LabeledStmt
  type Noop = EmptyStmt
  type Return = ReturnStmt
  type SpecialConstructorInvocation = ExplicitConstructorInvocationStmt
  type Statement = com.github.javaparser.ast.stmt.Statement
}
