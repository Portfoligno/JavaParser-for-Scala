package jp4s.syntax

import nejc4s.base.Optional

private[jp4s]
trait OptionalSyntax {
  implicit def toOptionalOps[A](optional: Optional[A]): OptionalOps[A] =
    new OptionalOps[A](optional)
}

private[jp4s]
class OptionalOps[A](private val optional: Optional[A]) extends AnyVal {
  def covary[B >: A]: Optional[B] =
    optional.asInstanceOf[Optional[B]]

  def transform[B](f: A => B): Optional[B] =
    optional.map(f(_))

  def orElseNull(implicit ev: Null <:< A): A =
    optional.orElse(ev(null))
}
