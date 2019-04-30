package jp4s.syntax

import jp4s.ast.`type`.{ArrayDimensions, ArrayType}
import jp4s.ast.declaration.Variable
import jp4s.extra.ast.`type`.NestedArrayType

trait VariableSyntax {
  implicit def toVariableOps(v: Variable): VariableOps =
    new VariableOps(v)
}

class VariableOps(private val v: Variable) extends AnyVal {
  def arrayDimensions: ArrayDimensions =
    NestedArrayType.unapply(v.getType)(ArrayType.OnName).get._2
}
