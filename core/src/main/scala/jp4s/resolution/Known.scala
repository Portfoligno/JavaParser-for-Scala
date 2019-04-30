package jp4s.resolution

object Known {
  def unapply[A, B](a: A)(implicit A: Resolution.ByInput.Aux[A, B]): Option[B] =
    A.resolve(a).toOption
}
