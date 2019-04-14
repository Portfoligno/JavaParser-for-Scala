package jp4s.ast.expression

object BooleanLiteral {
  case object True extends Variance(true)
  case object False extends Variance(false)


  def apply(variance: Variance): BooleanLiteral =
    variance()

  def unapply(l: BooleanLiteral): Some[Variance] =
    Some(Variance(l.getValue))


  object Variance {
    def apply(value: Boolean): Variance =
      if (value) True else False

    def unapply(v: Variance): Some[Boolean] =
      Some(v.value)
  }

  sealed abstract class Variance(private val value: Boolean) {
    def apply(): BooleanLiteral =
      new BooleanLiteral(value)

    def unapply(l: BooleanLiteral): Boolean =
      !l.getValue ^ value
  }
}
