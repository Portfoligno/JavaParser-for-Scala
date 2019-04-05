package jp4s.ast
package declaration

import com.github.javaparser.ast.expr.Name
import jp4s.ast.expression.Annotation
import nejc4s.base.JavaList

object ReceiverParameter {
  def apply(
    annotations: JavaList[Annotation],
    `type`: Type,
    name: Name
  ): ReceiverParameter =
    new ReceiverParameter(
      nodeList(annotations),
      `type`,
      name
    )

  def unapply(p: ReceiverParameter): Option[(
    JavaList[Annotation],
    Type,
    Name
  )] =
    Some((p.getAnnotations, p.getType, p.getName))
}
