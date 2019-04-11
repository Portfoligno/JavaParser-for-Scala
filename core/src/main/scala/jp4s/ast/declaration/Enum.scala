package jp4s.ast
package declaration

import jp4s.ast.expression.{Annotation, Expression}
import nejc4s.base.JavaList

object Enum {
  def apply(
    annotations: JavaList[Annotation],
    name: Identifier,
    arguments: JavaList[Expression],
    classBody: JavaList[Body]
  ): Enum =
    new Enum(
      nodeList(annotations),
      simpleNameNode(name),
      nodeList(arguments),
      nodeList(classBody)
    )

  def unapply(c: Enum): Some[(
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
