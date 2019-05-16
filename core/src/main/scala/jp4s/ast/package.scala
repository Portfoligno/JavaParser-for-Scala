package jp4s

import com.github.javaparser.ast.NodeList
import nejc4s.NonEmptyJavaList
import nejc4s.base._

import scala.annotation.tailrec

package object ast {
  type CompilationUnit = com.github.javaparser.ast.CompilationUnit
  type Modifier = com.github.javaparser.ast.Modifier
  type Node = com.github.javaparser.ast.Node


  private[ast]
  type NameNode = com.github.javaparser.ast.expr.Name

  private[ast]
  type SimpleNameNode = com.github.javaparser.ast.expr.SimpleName


  type Identifier <: String with Identifier.Tag

  object Identifier {
    private[ast] trait Tag extends Any

    private[jp4s]
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
  import scala.collection.convert.ImplicitConversionsToScala._

  private[ast]
  def identifier(n: SimpleNameNode): Identifier =
    Identifier.unsafeFromString(n.getIdentifier)

  private[ast]
  def simpleNameNode(identifier: Identifier): SimpleNameNode =
    SimpleNameNode(identifier)


  private[ast]
  def identifiers(n: NameNode): NonEmptyJavaList[Identifier] = {
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

    new NonEmptyJavaList.UnsafeWrapper(builder.result().view.reverse.asJava)
  }

  private[ast]
  def nameNode(identifiers: NonEmptyJavaList[Identifier]): NameNode = {
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
  def nejl[A <: Node](nodeList: NodeList[A]): NonEmptyJavaList[A] =
    NodeNejlWrapper(nodeList)

  private[ast]
  def nodeList[A <: Node](javaList: JavaList[A]): NodeList[A] =
    javaList match {
      case nodeList: NodeList[A] =>
        nodeList

      case NodeNejlWrapper(nodeList) =>
        nodeList

      case _ =>
        new NodeList(javaList)
    }


  private[ast]
  def uncons[A <: Node](nodeList: NodeList[A]): (A, Seq[A]) =
    (nodeList.iterator.next(), NodeTailJavaListWrapper(nodeList))

  private[ast]
  def cons[A <: Node](head: A, tail: Seq[A]): NodeList[A] =
    tail.asJava match {
      case NodeTailJavaListWrapper(source @ NonEmptyJavaList(`head`, _ @ _*)) =>
        source

      case _ =>
        new NodeList((head +: tail.view).asJava)
    }


  private[ast]
  def nameNodesAsIdentifiers(nameNodes: NodeList[NameNode]): JavaList[NonEmptyJavaList[Identifier]] =
    NameNodeNejlWrapper(nameNodes)

  private[ast]
  def identifiersAsNameNodes(identifiers: JavaList[NonEmptyJavaList[Identifier]]): NodeList[NameNode] =
    identifiers match {
      case NameNodeNejlWrapper(nodeList) =>
        nodeList

      case _ =>
        new NodeList(identifiers.map(nameNode).asJava)
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
    import nejc4s.syntax.optional._

    def apply(qualifier: Optional[NameNode], identifier: Identifier): NameNode =
      new NameNode(qualifier.orElseNull, identifier)

    def unapply(n: NameNode): Some[(Optional[NameNode], Identifier)] =
      Some((n.getQualifier, Identifier.unsafeFromString(n.getIdentifier)))
  }



  private
  case class NodeNejlWrapper[A <: Node](
    override protected val delegate: NodeList[A]
  )
    extends NonEmptyJavaList.UnsafeProxy[A]

  private
  case class NodeTailJavaListWrapper[A <: Node](
    source: NodeList[A]
  )
    extends JavaList.UnsafeProxy[A] {
    protected
    override def delegate: JavaList[A] =
      source.view.drop(1).asJava
  }

  private
  case class NameNodeNejlWrapper(
    source: NodeList[NameNode]
  )
    extends NonEmptyJavaList.UnsafeProxy[NonEmptyJavaList[Identifier]] {
    protected
    override def delegate: JavaList[NonEmptyJavaList[Identifier]] =
      source.view.map(identifiers).asJava
  }
}
