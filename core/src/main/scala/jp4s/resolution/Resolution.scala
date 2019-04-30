package jp4s.resolution

import com.github.javaparser.resolution.{Resolvable, UnsolvedSymbolException}

trait Resolution[A, B] {
  def resolve: A => Either[Throwable, B]
}

object Resolution {
  @inline
  def apply[A, B](implicit A: Resolution[A, B]): Resolution[A, B] = A


  private
  object ResolvableResolution extends Resolution[Resolvable[Any], Any] {
    override
    def resolve: Resolvable[Any] => Either[Throwable, Any] =
      a =>
        try {
          Right(a.resolve)
        } catch {
          case e: UnsolvedSymbolException =>
            Left(e)
        }
  }

  implicit def jp4sResolvableResolutionInstance[A, B](
    implicit
    ev: A <:< Resolvable[_] { def resolve(): B }
  ): Resolution[A, B] =
    ResolvableResolution.asInstanceOf[Resolution[A, B]]



  trait ByInput[A] {
    type Out

    def resolve: A => Either[Throwable, Out]
  }

  object ByInput {
    type Aux[A, B] = ByInput[A] {
      type Out = B
    }

    @inline
    def apply[A](implicit A: Resolution.ByInput[A]): Resolution.ByInput[A] = A


    implicit def jp4sResolutionByInputInstance[A, B](
      implicit
      A: Resolution[A, B]
    ): Resolution.ByInput.Aux[A, B] =
      new ByInput[A] {
        override
        type Out = B

        override
        def resolve: A => Either[Throwable, B] =
          A.resolve
      }
  }
}
