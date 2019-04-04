package jp4s.ast
package declaration

import jp4s.ast.`type`.{ReferenceType, TypeParameter}
import jp4s.ast.expression.Annotation
import jp4s.utility.{JavaList, Optional}

object Callable {
  def unapply(c: Callable): Some[(
    JavaList[Modifier],
    JavaList[Annotation],
    JavaList[TypeParameter],
    Identifier,
    JavaList[Parameter],
    JavaList[ReferenceType],
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
