package jp4s.utility

import scala.collection.JavaConverters._

object JavaList {
  def apply[A](xs: A*): JavaList[A] =
    xs.asJava

  def unapplySeq[A](xs: JavaList[A]): Option[Seq[A]] =
    Some(xs.asScala)
}
