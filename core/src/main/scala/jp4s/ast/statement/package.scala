package jp4s.ast

import com.github.javaparser.ast.stmt.{AssertStmt, BlockStmt, BreakStmt}

package object statement {
  type Assert = AssertStmt
  type Block = BlockStmt
  type Break = BreakStmt
}
