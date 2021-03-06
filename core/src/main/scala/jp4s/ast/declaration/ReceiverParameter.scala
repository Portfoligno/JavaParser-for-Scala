package jp4s.ast
package declaration

import jp4s.ast.`type`.Type
import jp4s.ast.expression.Annotation
import nejc4s.alias.Nejl
import nejc4s.base.JavaList

object ReceiverParameter {
  def apply(
    annotations: JavaList[Annotation],
    `type`: Type,
    name: Nejl[Identifier]
  ): ReceiverParameter =
    new ReceiverParameter(
      nodeList(annotations),
      `type`,
      nameNode(name)
    )

  def unapply(p: ReceiverParameter): Some[(
    JavaList[Annotation],
    Type,
    Nejl[Identifier]
  )] =
    Some((
      p.getAnnotations,
      p.getType,
      identifiers(p.getName)
    ))
}
