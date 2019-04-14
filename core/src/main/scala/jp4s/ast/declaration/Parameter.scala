package jp4s.ast
package declaration

import jp4s.ast.`type`.Type
import jp4s.ast.expression.Annotation
import nejc4s.base.JavaList

object Parameter {
  case object Plain extends Variance(false)
  case object VarArgs extends Variance(true)


  def apply(
    variance: Parameter.Variance,
    modifiers: JavaList[Modifier],
    annotations: JavaList[Annotation],
    `type`: Type,
    varArgsAnnotations: JavaList[Annotation],
    name: Identifier
  ): Parameter =
    variance(modifiers, annotations, `type`, varArgsAnnotations, name)

  def unapply(p: Parameter): Option[(
    Parameter.Variance,
    JavaList[Modifier],
    JavaList[Annotation],
    Type,
    JavaList[Annotation],
    Identifier
  )] =
    Some((
      Variance(p.isVarArgs),
      p.getModifiers,
      p.getAnnotations,
      p.getType,
      p.getVarArgsAnnotations,
      identifier(p.getName)
    ))


  object Variance {
    def apply(isVarArgs: Boolean): Variance =
      if (isVarArgs) VarArgs else Plain

    def unapply(v: Variance): Some[Boolean] =
      Some(v.isVarArgs)
  }

  sealed abstract class Variance(private val isVarArgs: Boolean) {
    def apply(
      modifiers: JavaList[Modifier],
      annotations: JavaList[Annotation],
      `type`: Type,
      varArgsAnnotations: JavaList[Annotation],
      name: Identifier
    ): Parameter =
      new Parameter(
        nodeList(modifiers),
        nodeList(annotations),
        `type`,
        isVarArgs,
        nodeList(varArgsAnnotations),
        simpleNameNode(name)
      )

    def unapply(p: Parameter): Option[(
      JavaList[Modifier],
      JavaList[Annotation],
      Type,
      JavaList[Annotation],
      Identifier
    )] =
      if (p.isVarArgs ^ isVarArgs) {
        None
      } else {
        Some((
          p.getModifiers,
          p.getAnnotations,
          p.getType,
          p.getVarArgsAnnotations,
          identifier(p.getName)
        ))
      }
  }
}
