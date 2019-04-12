package jp4s.ast
package declaration

import nejc4s.alias.Nejl

object Import {
  def unapply(i: Import): Some[(
    Nejl[Identifier],
    Boolean
  )] =
    Some((
      identifiers(i.getName),
      i.isAsterisk
    ))


  object Plain {
    def apply(
      name: Nejl[Identifier],
      isAsterisk: Boolean
    ): Import =
      new Import(
        nameNode(name),
        false,
        isAsterisk
      )

    def unapply(i: Import): Option[(
      Nejl[Identifier],
      Boolean
    )] =
      if (!i.isStatic) {
        Some((
          identifiers(i.getName),
          i.isAsterisk
        ))
      } else {
        None
      }
  }

  object Static {
    def apply(
      name: Nejl[Identifier],
      isAsterisk: Boolean
    ): Import =
      new Import(
        nameNode(name),
        true,
        isAsterisk
      )

    def unapply(i: Import): Option[(
      Nejl[Identifier],
      Boolean
    )] =
      if (i.isStatic) {
        Some((
          identifiers(i.getName),
          i.isAsterisk
        ))
      } else {
        None
      }
  }
}
