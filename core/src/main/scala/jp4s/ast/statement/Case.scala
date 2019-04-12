package jp4s.ast
package statement

import com.github.javaparser.ast.NodeList
import com.github.javaparser.ast.stmt.SwitchEntry
import com.github.javaparser.ast.stmt.SwitchEntry.Type._
import jp4s.ast.expression.Expression
import nejc4s.base.JavaList

object Case {
  type Type = SwitchEntry.Type

  def apply(
    labels: JavaList[Expression],
    body: Either[JavaList[Statement], Statement]
  ): Case =
    body match {
      case Left(statements) =>
        new Case(
          nodeList(labels),
          STATEMENT_GROUP,
          nodeList(statements)
        )

      case Right(statement) =>
        new Case(
          nodeList(labels),
          statement match {
            case _: Execution => EXPRESSION
            case _: Block => BLOCK
            case _: Throw => THROWS_STATEMENT
            case r @ _ =>
              throw new IllegalArgumentException(String.valueOf(r))
          },
          new NodeList(statement)
        )
    }

  def unapply(a: Case): Some[(
    JavaList[Expression],
    Either[JavaList[Statement], Statement]
  )] =
    Some((
      a.getLabels,
      (
        a.getType match {
          case STATEMENT_GROUP => Left(a.getStatements)
          case EXPRESSION => Right((s: Statement) => s.asInstanceOf[Execution])
          case BLOCK => Right((s: Statement) => s.asInstanceOf[Block])
          case THROWS_STATEMENT => Right((s: Statement) => s.asInstanceOf[Throw])
        }
      )
        .map(_(a.getStatements match {
          case JavaList(statement) => statement
          case r @ _ =>
            throw new IllegalArgumentException(String.valueOf(r))
        }))
    ))
}
