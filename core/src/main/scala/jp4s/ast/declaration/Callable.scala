package jp4s.ast.declaration

import java.util.Optional

import com.github.javaparser.ast.`type`.{ReferenceType, TypeParameter}
import com.github.javaparser.ast.body.{Parameter, ReceiverParameter}
import jp4s.ast.name.Simple
import jp4s.ast.{Identifier, Modifier, NodeList, expression}

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
      Simple.identifier(c.getName),
      c.getParameters,
      c.getThrownExceptions,
      c.getReceiverParameter
    ))
}
