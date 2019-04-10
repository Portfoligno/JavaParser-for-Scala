package jp4s.ast
package expression

import nejc4s.base.JavaList

object ArrayInitializer {
  def apply(values: JavaList[Expression]): ArrayInitializer =
    new ArrayInitializer(nodeList(values))

  def unapply(i: ArrayInitializer): Some[JavaList[Expression]] =
    Some(i.getValues)
}
