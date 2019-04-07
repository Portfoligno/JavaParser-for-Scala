package jp4s.syntax

import nejc4s.base.Optional

private[jp4s]
trait OptionalSyntax {
  implicit def toOptionalOps[A](optional: Optional[A]): OptionalOps[A] =
    new OptionalOps[A](optional)
}

private[jp4s]
class OptionalOps[A](private val optional: Optional[A]) extends AnyVal {
  def orElseNull(implicit ev: Null <:< A): A =
    optional.orElse(ev(null))
}
