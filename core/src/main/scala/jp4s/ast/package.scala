package jp4s

import com.github.javaparser.ast.NodeList
import nejc4s.alias.Nejl
import nejc4s.base.{Absent, JavaCollection, JavaList, Optional, Present}

import scala.annotation.tailrec

package object ast {
  type ArrayCreationLevel = com.github.javaparser.ast.ArrayCreationLevel
  type Modifier = com.github.javaparser.ast.Modifier
  type NameNode = com.github.javaparser.ast.expr.Name
  type Node = com.github.javaparser.ast.Node
  type SimpleNameNode = com.github.javaparser.ast.expr.SimpleName


  type Identifier <: String with Identifier.Tag

  object Identifier {
    private[ast] trait Tag extends Any

    private
    def isIdentifier(s: String): Boolean =
      s.nonEmpty &&
        Character.isJavaIdentifierStart(s.head) &&
        s.tail.forall(Character.isJavaIdentifierPart)

    def fromString(s: String): Option[Identifier] =
      if (isIdentifier(s)) {
        Some(s.asInstanceOf[Identifier])
      } else {
        None
      }

    def unsafeFromString(s: String): Identifier =
      if (isIdentifier(s)) {
        s.asInstanceOf[Identifier]
      } else {
        throw new IllegalArgumentException(String.valueOf(s))
      }
  }



  import scala.collection.JavaConverters._

  private[ast]
  def identifier(n: SimpleNameNode): Identifier =
    Identifier.unsafeFromString(n.getIdentifier)

  private[ast]
  def simpleNameNode(identifier: Identifier): SimpleNameNode =
    SimpleNameNode(identifier)


  private[ast]
  def identifiers(n: NameNode): Nejl[Identifier] = {
    val builder = Vector.newBuilder[Identifier]

    @tailrec
    def go(current: NameNode): Unit = {
      builder += Identifier.unsafeFromString(current.getIdentifier)
      val next = current.getQualifier

      if (next.isPresent) {
        go(next.get)
      }
    }
    go(n)

    new Nejl.UnsafeWrapper(builder.result().view.reverse.asJava)
  }

  private[ast]
  def nameNode(identifiers: Nejl[Identifier]): NameNode = {
    val iterator = identifiers.iterator()

    @tailrec
    def append(base: NameNode): NameNode =
      if (iterator.hasNext) {
        append(NameNode(Present(base), iterator.next()))
      } else {
        base
      }

    append(NameNode(Absent(), iterator.next()))
  }


  private[ast]
  def nejl[A <: Node](nodeList: NodeList[A]): Nejl[A] =
    NodeListNejlProxy(nodeList)

  private[ast]
  def nodeList[A <: Node](javaList: JavaList[A]): NodeList[A] =
    javaList match {
      case nodeList: NodeList[A] =>
        nodeList

      case NodeListNejlProxy(nodeList) =>
        nodeList

      case _ =>
        new NodeList(javaList)
    }



  private[ast]
  object SimpleNameNode {
    def apply(identifier: Identifier): SimpleNameNode =
      new SimpleNameNode(identifier)

    def unapply(s: SimpleNameNode): Some[Identifier] =
      Some(identifier(s))
  }

  private[ast]
  object NameNode {
    import jp4s.syntax.optional._

    def apply(qualifier: Optional[NameNode], identifier: Identifier): NameNode =
      new NameNode(qualifier.orElseNull, identifier)

    def unapply(n: NameNode): Some[(Optional[NameNode], Identifier)] =
      Some((n.getQualifier, Identifier.unsafeFromString(n.getIdentifier)))
  }



  private
  case class NodeListNejlProxy[A <: Node](
    override protected val delegate: NodeList[A]
  )
    extends Nejl.UnsafeProxy[A]
      with JavaList[A]
      with JavaCollection[A]
}
