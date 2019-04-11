package jp4s.ast

import com.github.javaparser.ast.stmt._

package object statement {
  type Assert = AssertStmt
  type Block = BlockStmt
  type Break = BreakStmt
  type Case = SwitchEntry
  type Catch = CatchClause
  type Continue = ContinueStmt
  type DeclareLocal = LocalClassDeclarationStmt
  type Do = DoStmt
  type Execution = ExpressionStmt
  type For = ForStmt
  type ForEach = ForEachStmt
  type If = IfStmt
  type Label = LabeledStmt
  type Noop = EmptyStmt
  type Return = ReturnStmt
  type SpecialConstructorInvocation = ExplicitConstructorInvocationStmt
  type Statement = com.github.javaparser.ast.stmt.Statement
  type SwitchStatement = SwitchStmt
  type Synchronized = SynchronizedStmt
  type Throw = ThrowStmt
  type Try = TryStmt
  type While = WhileStmt
}
