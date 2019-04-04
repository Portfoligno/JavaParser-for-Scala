package jp4s.utility

class OptionalOps[A](private val optional: Optional[A]) extends AnyVal {
  def orElseNull: A =
    optional.orElse(null.asInstanceOf[A])
}
