package jp4s.ast
package expression

import com.github.javaparser.ast.body.Parameter
import com.github.javaparser.ast.stmt.Statement
import nejc4s.base.JavaList

object Lambda {
  def apply(
    parameters: JavaList[Parameter],
    body: Statement,
    isEnclosingParameters: Boolean
  ): Lambda =
    new Lambda(nodeList(parameters), body, isEnclosingParameters)

  def unapply(l: Lambda): Some[(
    JavaList[Parameter],
    Statement,
    Boolean
  )] =
    Some((l.getParameters, l.getBody, l.isEnclosingParameters))
}
