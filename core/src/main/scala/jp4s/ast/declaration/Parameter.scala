package jp4s.ast
package declaration

import jp4s.ast.`type`.Type
import jp4s.ast.expression.Annotation
import nejc4s.base.JavaList

object Parameter {
  def apply(
    modifiers: JavaList[Modifier],
    annotations: JavaList[Annotation],
    `type`: Type,
    isVarArgs: Boolean,
    varArgsAnnotations: JavaList[Annotation],
    name: Identifier
  ): Parameter =
    new Parameter(
      nodeList(modifiers),
      nodeList(annotations),
      `type`,
      isVarArgs,
      nodeList(varArgsAnnotations),
      simpleName(name)
    )

  def unapply(p: Parameter): Option[(
    JavaList[Modifier],
    JavaList[Annotation],
    Type,
    Boolean,
    JavaList[Annotation],
    Identifier
  )] =
    Some((
      p.getModifiers,
      p.getAnnotations,
      p.getType,
      p.isVarArgs,
      p.getVarArgsAnnotations,
      identifier(p.getName)
    ))
}
