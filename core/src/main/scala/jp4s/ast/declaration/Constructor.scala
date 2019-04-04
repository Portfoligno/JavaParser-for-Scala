package jp4s.ast
package declaration

import jp4s.ast.`type`.{ReferenceType, TypeParameter}
import jp4s.ast.expression.{Annotation, SimpleName}
import jp4s.ast.statement.Block
import jp4s.utility.{JavaList, Optional}

object Constructor {
  def apply(
    modifiers: JavaList[Modifier],
    annotations: JavaList[Annotation],
    typeParameters: JavaList[TypeParameter],
    name: Identifier,
    parameters: JavaList[Parameter],
    thrownExceptions: JavaList[ReferenceType],
    body: Block,
    receiverParameter: ReceiverParameter
  ): Constructor =
    new Constructor(
      nodeList(modifiers),
      nodeList(annotations),
      nodeList(typeParameters),
      SimpleName(name),
      nodeList(parameters),
      nodeList(thrownExceptions),
      body,
      receiverParameter
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
