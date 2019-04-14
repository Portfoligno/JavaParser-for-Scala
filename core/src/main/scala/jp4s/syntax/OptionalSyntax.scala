package jp4s.syntax

import java.util.Collections

import nejc4s.base.{JavaList, Optional, Present}

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

  def toJavaList: JavaList[A] =
    optional match {
      case Present(a) => Collections.singletonList(a)
      case _ => Collections.emptyList()
    }
}
