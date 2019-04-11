package jp4s.ast

import com.github.javaparser.ast.stmt.{AssertStmt, BlockStmt, BreakStmt, ContinueStmt, DoStmt}

package object statement {
  type Assert = AssertStmt
  type Block = BlockStmt
  type Break = BreakStmt
  type CatchClause = com.github.javaparser.ast.stmt.CatchClause
  type Continue = ContinueStmt
  type Do = DoStmt

  type Statement = com.github.javaparser.ast.stmt.Statement
}
