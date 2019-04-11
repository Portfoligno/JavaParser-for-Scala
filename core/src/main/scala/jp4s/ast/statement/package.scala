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
  type ForEach = ForEachStmt
  type If = IfStmt
  type Label = LabeledStmt
  type Return = ReturnStmt

  type Noop = EmptyStmt

  type SpecialConstructorInvocation = ExplicitConstructorInvocationStmt

  type Statement = com.github.javaparser.ast.stmt.Statement
}
