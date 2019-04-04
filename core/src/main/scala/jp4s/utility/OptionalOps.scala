package jp4s.utility

class OptionalOps[A](private val optional: Optional[A]) extends AnyVal {
  def orElseNull(implicit ev: Null <:< A): A =
    optional.orElse(ev(null))
}
