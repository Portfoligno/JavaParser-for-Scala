package jp4s.ast

import com.github.javaparser.ast.stmt._

package object statement {
  type Assert = AssertStmt
  type Block = BlockStmt
  type Break = BreakStmt
  type CatchClause = com.github.javaparser.ast.stmt.CatchClause
  type Continue = ContinueStmt
  type Do = DoStmt
  type ExplicitConstructorCall = ExplicitConstructorInvocationStmt
  type ForEach = ForEachStmt

  type Noop = EmptyStmt

  type Statement = com.github.javaparser.ast.stmt.Statement
}
