package jp4s.ast
package expression

import jp4s.ast.`type`.Type
import jp4s.ast.declaration.Variable
import nejc4s.alias.Nejl
import nejc4s.base.JavaList

object Variables {
  def apply(
    modifiers: JavaList[Modifier],
    annotations: JavaList[Annotation],
    `type`: Type,
    variables: Nejl[Variable]
  ): Variables =
    new Variables(
      nodeList(modifiers),
      nodeList(annotations),
      Variable.nodeList(
        `type`,
        variables
      )
    )

  def unapply(v: Variables): Some[(
    JavaList[Modifier],
    JavaList[Annotation],
    Type,
    Nejl[Variable]
  )] =
    Some((
      v.getModifiers,
      v.getAnnotations,
      Variable.`type`(v.getVariables),
      Variable.nejl(v.getVariables)
    ))
}
