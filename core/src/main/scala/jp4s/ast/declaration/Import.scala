package jp4s.ast
package declaration

import nejc4s.alias.Nejl

object Import {
  case object Plain extends Variance(false)
  case object Static extends Variance(true)

  def apply(
    variance: Import.Variance,
    name: Nejl[Identifier],
    isAsterisk: Boolean
  ): Import =
    variance(name, isAsterisk)

  def unapply(i: Import): Some[(
    Import.Variance,
    Nejl[Identifier],
    Boolean
  )] =
    Some((
      if (i.isStatic) Static else Plain,
      identifiers(i.getName),
      i.isAsterisk
    ))


  sealed abstract class Variance(private val isStatic: Boolean) {
    def apply(
      name: Nejl[Identifier],
      isAsterisk: Boolean
    ): Import =
      new Import(
        nameNode(name),
        isStatic,
        isAsterisk
      )

    def unapply(i: Import): Option[(
      Nejl[Identifier],
      Boolean
    )] =
      if (i.isStatic ^ isStatic) {
        None
      } else {
        Some((
          identifiers(i.getName),
          i.isAsterisk
        ))
      }
  }
}
