package jp4s.ast
package declaration

import jp4s.ast.`type`.{ReferenceType, TypeParameter}
import jp4s.ast.expression.Annotation
import jp4s.ast.statement.Block
import nejc4s.base.{JavaList, Optional}

object Constructor {
  import jp4s.syntax.optional._

  def apply(
    modifiers: JavaList[Modifier],
    annotations: JavaList[Annotation],
    typeParameters: JavaList[TypeParameter],
    name: Identifier,
    parameters: JavaList[Parameter],
    thrownExceptions: JavaList[ReferenceType],
    body: Block,
    receiverParameter: Optional[ReceiverParameter]
  ): Constructor =
    new Constructor(
      nodeList(modifiers),
      nodeList(annotations),
      nodeList(typeParameters),
      simpleNameNode(name),
      nodeList(parameters),
      nodeList(thrownExceptions),
      body,
      receiverParameter.orElseNull
    )

  def unapply(c: Constructor): Some[(
    JavaList[Modifier],
    JavaList[Annotation],
    JavaList[TypeParameter],
    Identifier,
    JavaList[Parameter],
    JavaList[ReferenceType],
    Block,
    Optional[ReceiverParameter]
  )] =
    Some((
      c.getModifiers,
      c.getAnnotations,
      c.getTypeParameters,
      identifier(c.getName),
      c.getParameters,
      c.getThrownExceptions,
      c.getBody,
      c.getReceiverParameter
    ))
}
