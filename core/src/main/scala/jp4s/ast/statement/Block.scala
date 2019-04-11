package jp4s.ast
package statement

import nejc4s.base.JavaList

object Block {
  def apply(statements: JavaList[Statement]): Block =
    new Block(nodeList(statements))

  def unapply(b: Block): Some[JavaList[Statement]] =
    Some(b.getStatements)
}