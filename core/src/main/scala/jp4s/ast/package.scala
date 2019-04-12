package jp4s

import com.github.javaparser.ast.NodeList
import nejc4s.alias.Nejl
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
  import scala.collection.convert.ImplicitConversionsToScala._

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
    NodeNejlProxy(nodeList)

  private[ast]
  def nodeList[A <: Node](javaList: JavaList[A]): NodeList[A] =
    javaList match {
      case nodeList: NodeList[A] =>
        nodeList

      case NodeNejlProxy(nodeList) =>
        nodeList

      case _ =>
        new NodeList(javaList)
    }

  private[ast]
  def nameNodesAsIdentifiers(nameNodes: NodeList[NameNode]): JavaList[Nejl[Identifier]] =
    NameNodeNejlProxy(nameNodes)

  private[ast]
  def identifiersAsNameNodes(identifiers: JavaList[Nejl[Identifier]]): NodeList[NameNode] =
    identifiers match {
      case NameNodeNejlProxy(nodeList) =>
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
    import jp4s.syntax.optional._

    def apply(qualifier: Optional[NameNode], identifier: Identifier): NameNode =
      new NameNode(qualifier.orElseNull, identifier)

    def unapply(n: NameNode): Some[(Optional[NameNode], Identifier)] =
      Some((n.getQualifier, Identifier.unsafeFromString(n.getIdentifier)))
  }



  private
  case class NodeNejlProxy[A <: Node](
    override protected val delegate: NodeList[A]
  )
    extends Nejl.UnsafeProxy[A]
      with JavaList[A]
      with JavaCollection[A]

  private
  case class NameNodeNejlProxy(
    source: NodeList[NameNode]
  )
    extends Nejl.UnsafeProxy[Nejl[Identifier]]
      with JavaList[Nejl[Identifier]]
      with JavaCollection[Nejl[Identifier]] {
    protected
    override def delegate: JavaList[Nejl[Identifier]] =
      source.view.map(identifiers).asJava
  }
}
