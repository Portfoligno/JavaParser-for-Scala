package jp4s.ast
package declaration

import jp4s.ast.`type`.{ReferenceType, TypeParameter}
import jp4s.utility.Optional

object Callable {
  def unapply(c: Callable): Some[(
    NodeList[Modifier],
    NodeList[expression.Annotation],
    NodeList[TypeParameter],
    Identifier,
    NodeList[Parameter],
    NodeList[ReferenceType],
    Optional[ReceiverParameter]
  )] =
    Some((
      c.getModifiers,
      c.getAnnotations,
      c.getTypeParameters,
      identifier(c.getName),
      c.getParameters,
      c.getThrownExceptions,
      c.getReceiverParameter
    ))
}
