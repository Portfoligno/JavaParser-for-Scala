package jp4s.ast
package declaration

import jp4s.ast.`type`.{ReferenceType, TypeParameter}
import jp4s.ast.expression.Annotation
import jp4s.ast.statement.Block
import jp4s.utility._

object Method {
  def apply(
    modifiers: JavaList[Modifier],
    annotations: JavaList[Annotation],
    typeParameters: JavaList[TypeParameter],
    `type`: Type,
    name: Identifier,
    parameters: JavaList[Parameter],
    thrownExceptions: JavaList[ReferenceType],
    body: Optional[Block],
    receiverParameter: Optional[ReceiverParameter]
  ): Method =
    new Method(
      nodeList(modifiers),
      nodeList(annotations),
      nodeList(typeParameters),
      `type`,
      SimpleName(name),
      nodeList(parameters),
      nodeList(thrownExceptions),
      body.orElseNull,
      receiverParameter.orElseNull
    )

  def unapply(m: Method): Option[(
    JavaList[Modifier],
    JavaList[Annotation],
    JavaList[TypeParameter],
    Type,
    Identifier,
    JavaList[Parameter],
    JavaList[ReferenceType],
    Optional[Block],
    Optional[ReceiverParameter]
  )] =
    Some((
      m.getModifiers,
      m.getAnnotations,
      m.getTypeParameters,
      m.getType,
      identifier(m.getName),
      m.getParameters,
      m.getThrownExceptions,
      m.getBody,
      m.getReceiverParameter
    ))
}
