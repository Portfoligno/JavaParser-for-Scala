package jp4s.resolution

object Textual {
  def unapply[A, B](a: A)(implicit A: Resolution.ByInput.Aux[A, B]): Option[B] =
    A.resolve(a).toOption
}
