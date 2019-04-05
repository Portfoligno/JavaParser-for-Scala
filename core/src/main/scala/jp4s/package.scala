import nejc4s.base.Optional

package object jp4s {
  private[jp4s]
  implicit def toOptionalOps[A](optional: Optional[A]): OptionalOps[A] =
    new OptionalOps[A](optional)

  private[jp4s]
  class OptionalOps[A](private val optional: Optional[A]) extends AnyVal {
    def orElseNull(implicit ev: Null <:< A): A =
      optional.orElse(ev(null))
  }
}
