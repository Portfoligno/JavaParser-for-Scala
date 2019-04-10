package jp4s.ast.expression

import jp4s.ast.`type`.Type

object ClassAccess {
  def apply(`type`: Type): ClassAccess =
    new ClassAccess(`type`)

  def unapply(a: ClassAccess): Some[Type] =
    Some(a.getType)
}
