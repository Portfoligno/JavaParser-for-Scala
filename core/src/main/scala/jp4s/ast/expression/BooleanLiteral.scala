package jp4s.ast.expression

object BooleanLiteral {
  def unapply(l: BooleanLiteral): Boolean =
    true


  object True {
    def apply(): BooleanLiteral =
      new BooleanLiteral(true)

    def unapply(l: BooleanLiteral): Boolean =
      l.getValue
  }

  object False {
    def apply(): BooleanLiteral =
      new BooleanLiteral(false)

    def unapply(l: BooleanLiteral): Boolean =
      !l.getValue
  }
}
