package jp4s.ast

import com.github.javaparser.ast.stmt.{AssertStmt, BlockStmt}

package object statement {
  type Assert = AssertStmt

  type Block = BlockStmt
}
