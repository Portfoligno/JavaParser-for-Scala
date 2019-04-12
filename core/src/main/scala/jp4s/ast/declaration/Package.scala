package jp4s.ast
package declaration

import jp4s.ast.expression.Annotation
import nejc4s.alias.Nejl
import nejc4s.base.JavaList

object Package {
  def apply(
    annotations: JavaList[Annotation],
    name: Nejl[Identifier]
  ): Package =
    new Package(
      nodeList(annotations),
      nameNode(name)
    )

  def unapply(p: Package): Some[(
    JavaList[Annotation],
    Nejl[Identifier]
  )] =
    Some((
      p.getAnnotations,
      identifiers(p.getName)
    ))
}
