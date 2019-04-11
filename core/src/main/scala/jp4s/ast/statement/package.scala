package jp4s.ast

import com.github.javaparser.ast.stmt._

package object statement {
  type Assert = AssertStmt
  type Block = BlockStmt
  type Break = BreakStmt
  type CatchClause = com.github.javaparser.ast.stmt.CatchClause
  type Continue = ContinueStmt
  type DeclareLocal = LocalClassDeclarationStmt
  type Do = DoStmt
  type ExplicitConstructorInvocation = ExplicitConstructorInvocationStmt
  type ForEach = ForEachStmt
  type If = IfStmt
  type Label = LabeledStmt
  type Return = ReturnStmt

  type Noop = EmptyStmt

  type Statement = com.github.javaparser.ast.stmt.Statement
}
