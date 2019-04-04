package jp4s.ast
package declaration

import jp4s.ast.expression.Annotation
import jp4s.utility.JavaList

object Fields {
  def apply(
    modifiers: JavaList[Modifier],
    annotations: JavaList[Annotation],
    `type`: Type,
    variables: JavaList[Variable]
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
    JavaList[Variable]
  )] =
    Some((
      f.getModifiers,
      f.getAnnotations,
      f.getVariable(0).getType,
      Variable.javaList(f.getVariables)
    ))
}
