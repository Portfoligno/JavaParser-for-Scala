package jp4s.utility

import java.util

import scala.collection.JavaConverters._
import scala.collection.convert.ImplicitConversionsToScala._

private[jp4s]
class MappedJavaList[A, B](val delegate: JavaList[A])(mapper: A => B) extends util.AbstractList[B] {
  private
  def view: Seq[B] = delegate.view.map(mapper)

  override def size: Int = delegate.size
  override def isEmpty: Boolean = delegate.isEmpty
  override def contains(o: Any): Boolean = view.contains(o)
  override def iterator(): util.Iterator[B] = view.iterator.asJava
  override def containsAll(c: util.Collection[_]): Boolean = view.asJava.containsAll(c)
  override def get(index: Int): B = mapper(delegate.get(index))
  override def indexOf(o: Any): Int = view.indexOf(o)
  override def lastIndexOf(o: Any): Int = view.lastIndexOf(o)
  override def listIterator(): util.ListIterator[B] = view.asJava.listIterator()
  override def listIterator(index: Int): util.ListIterator[B] = view.asJava.listIterator(index)
  override def subList(fromIndex: Int, toIndex: Int): JavaList[B] = delegate.view(fromIndex, toIndex).map(mapper).asJava

  override def hashCode(): Int = view.asJava.hashCode()
  override def equals(obj: Any): Boolean = view.asJava.equals(obj)
  override def toString: String = view.asJava.toString
}
