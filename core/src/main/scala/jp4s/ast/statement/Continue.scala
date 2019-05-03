package jp4s.ast
package statement

import nejc4s.base.Optional

object Continue {
  import nejc4s.syntax.optional._

  def apply(label: Optional[Identifier]): Continue =
    new Continue(
      label.transform(simpleNameNode).orElseNull
    )

  def unapply(c: Continue): Some[Optional[Identifier]] =
    Some(c.getLabel.map(identifier))
}
