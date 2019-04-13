package jp4s.ast
package declaration

import jp4s.ast.`type`.Type
import jp4s.ast.expression.Annotation
import nejc4s.base.JavaList

object Parameter {
  def unapply(p: Parameter): Some[(
    JavaList[Modifier],
    JavaList[Annotation],
    Type,
    JavaList[Annotation],
    Identifier
  )] =
    Some((
      p.getModifiers,
      p.getAnnotations,
      p.getType,
      p.getVarArgsAnnotations,
      identifier(p.getName)
    ))



  object Plain {
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
        false,
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
      if (!p.isVarArgs) {
        Parameter.unapply(p)
      } else {
        None
      }
  }


  object VarArgs {
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
        true,
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
      if (p.isVarArgs) {
        Parameter.unapply(p)
      } else {
        None
      }
  }
}
