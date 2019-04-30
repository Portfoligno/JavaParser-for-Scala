package jp4s.syntax

import jp4s.ast.`type`.ArrayType
import jp4s.ast.declaration.Variable
import jp4s.ast.expression.Annotation
import jp4s.extra.ast.`type`.NestedArrayType
import nejc4s.base.JavaList

trait VariableSyntax {
  implicit def toVariableOps(v: Variable): VariableOps =
    new VariableOps(v)
}

class VariableOps(private val v: Variable) extends AnyVal {
  def arrayDimensions: JavaList[JavaList[Annotation]] =
    NestedArrayType.unapply(v.getType)(ArrayType.OnName).get._2
}
