package jp4s.ast
package declaration

import jp4s.ast.`type`.Type
import jp4s.ast.expression.Annotation
import nejc4s.NonEmptyJavaList
import nejc4s.base.JavaList

object Fields {
  def apply(
    modifiers: JavaList[Modifier],
    annotations: JavaList[Annotation],
    `type`: Type,
    variables: NonEmptyJavaList[Variable]
  ): Fields =
    new Fields(
      nodeList(modifiers),
      nodeList(annotations),
      Variable.nodeList(
        `type`,
        variables
      )
    )

  def unapply(f: Fields): Some[(
    JavaList[Modifier],
    JavaList[Annotation],
    Type,
    NonEmptyJavaList[Variable]
  )] =
    Some((
      f.getModifiers,
      f.getAnnotations,
      Variable.`type`(f.getVariables),
      Variable.nejl(f.getVariables)
    ))
}
