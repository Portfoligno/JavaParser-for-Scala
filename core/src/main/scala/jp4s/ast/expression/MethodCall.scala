package jp4s.ast
package expression

import com.github.javaparser.ast.`type`.Type
import nejc4s.base.{JavaList, Optional}

object MethodCall {
  import jp4s.syntax.optional._

  def apply(
    scope: Optional[Expression],
    typeArguments: Optional[JavaList[Type]],
    name: Identifier,
    arguments: JavaList[Expression]
  ): MethodCall =
    new MethodCall(
      scope.orElseNull,
      typeArguments.transform(nodeList).orElseNull,
      simpleNameNode(name),
      nodeList(arguments)
    )

  def unapply(c: MethodCall): Some[(
    Optional[Expression],
    Optional[JavaList[Type]],
    Identifier,
    JavaList[Expression]
  )] =
    Some((
      c.getScope,
      c.getTypeArguments.covary,
      identifier(c.getName),
      c.getArguments
    ))
}
