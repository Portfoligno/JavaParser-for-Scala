package jp4s.ast
package statement

import jp4s.ast.expression.Expression
import nejc4s.base.{JavaList, Optional}

object Try {
  import jp4s.syntax.optional._

  def apply(
    resources: JavaList[Expression],
    tryBlock: Block,
    catchClauses: JavaList[Catch],
    finallyBlock: Optional[Block]
  ): Try =
    new Try(
      nodeList(resources),
      tryBlock,
      nodeList(catchClauses),
      finallyBlock.orElseNull
    )

  def unapply(t: Try): Some[(
    JavaList[Expression],
    Block,
    JavaList[Catch],
    Optional[Block]
  )] =
    Some((
      t.getResources,
      t.getTryBlock,
      t.getCatchClauses,
      t.getFinallyBlock
    ))
}
