package jp4s.ast
package declaration

import jp4s.ast.data.Identifier
import jp4s.ast.expression.Annotation
import nejc4s.base.JavaList

object EnumConstant {
  def apply(
    annotations: JavaList[Annotation],
    name: Identifier,
    arguments: JavaList[Expression],
    classBody: JavaList[Body]
  ): EnumConstant =
    new EnumConstant(
      nodeList(annotations),
      SimpleName(name),
      nodeList(arguments),
      nodeList(classBody)
    )

  def unapply(c: EnumConstant): Some[(
    JavaList[Annotation],
    Identifier,
    JavaList[Expression],
    JavaList[Body]
  )] =
    Some((
      c.getAnnotations,
      identifier(c.getName),
      c.getArguments,
      c.getClassBody
    ))
}
