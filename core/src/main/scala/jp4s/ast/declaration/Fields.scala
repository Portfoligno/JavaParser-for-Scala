package jp4s.ast
package declaration

import jp4s.ast.`type`.Type
import jp4s.ast.expression.Annotation
import nejc4s.alias.Nejl
import nejc4s.base.JavaList

object Fields {
  def apply(
    modifiers: JavaList[Modifier],
    annotations: JavaList[Annotation],
    `type`: Type,
    variables: Nejl[Variable]
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
    Nejl[Variable]
  )] =
    Some((
      f.getModifiers,
      f.getAnnotations,
      f.getVariable(0).getType,
      Variable.nejl(f.getVariables)
    ))
}
