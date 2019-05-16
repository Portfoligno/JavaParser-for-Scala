package jp4s.ast
package declaration

import jp4s.ast.expression.Annotation
import nejc4s.NonEmptyJavaList
import nejc4s.base.JavaList

private[declaration]
trait PackageFactory {
  def apply(
    annotations: JavaList[Annotation],
    name: NonEmptyJavaList[Identifier]
  ): Package =
    new Package(
      nodeList(annotations),
      nameNode(name)
    )

  def unapply(p: Package): Some[(
    JavaList[Annotation],
    NonEmptyJavaList[Identifier]
  )] =
    Some((
      p.getAnnotations,
      identifiers(p.getName)
    ))
}
