package jp4s.syntax

import jp4s.ast.Identifier
import jp4s.ast.Identifier.isIdentifier
import nejc4s.alias.Nejl

import scala.language.experimental.macros
import scala.reflect.macros.whitebox

trait IdentifierSyntax {
  implicit def toIdStringContext(sc: StringContext): IdStringContext =
    new IdStringContext(sc)
}

class IdStringContext(private val sc: StringContext) extends AnyVal {
  def id: IdInterpolator =
    macro IdMarco.check

  def ids: IdsInterpolator =
    macro IdMarco.checkMultiple
}

private
object IdMarco {
  def check(c: whitebox.Context): c.Expr[IdInterpolator] = {
    import c.universe._

    def IdInterpolator: TypeSymbol = symbolOf[IdInterpolator]
    def Identifier: TypeSymbol = symbolOf[Identifier]

    c.prefix.tree match {
      case Apply(_, List(Apply(_, List(literal @ Literal(Constant(s: String)))))) =>
        if (isIdentifier(s)) {
          c.Expr(q"""new $IdInterpolator($literal.asInstanceOf[$Identifier])""")
        } else {
          c.abort(c.enclosingPosition, s"$s is not an identifier")
        }
    }
  }

  def checkMultiple(c: whitebox.Context): c.Expr[IdsInterpolator] = {
    import c.universe._

    def IdsInterpolator: TypeSymbol = symbolOf[IdsInterpolator]
    def Identifier: TypeSymbol = symbolOf[Identifier]

    c.prefix.tree match {
      case Apply(_, List(Apply(_, List(Literal(Constant(s: String)))))) =>
        val parts = s.split('.')
        val invalid = parts.filterNot(isIdentifier)

        if (invalid.isEmpty) {
          val constants = parts.map(s => Literal(Constant(s))).view

          c.Expr(q"""
            new $IdsInterpolator(
              _root_.nejc4s.alias.Nejl(..$constants).asInstanceOf[_root_.nejc4s.alias.Nejl[$Identifier]]
            )
          """)
        } else {
          c.abort(
            c.enclosingPosition,
            invalid match {
              case Array(a) =>
                s"$a is not an identifier"

              case _ =>
                invalid.init.mkString("", ", ", s" and ${invalid.last} are not identifiers")
            }
          )
        }
    }
  }
}

class IdInterpolator(val value: Identifier) extends AnyVal {
  def apply(unused: Nothing*): Identifier =
    value

  def unapplySeq(s: String): Option[Seq[Nothing]] =
    if (s == value) {
      Some(Seq.empty)
    } else {
      None
    }
}

class IdsInterpolator(val value: Nejl[Identifier]) extends AnyVal {
  def apply(unused: Nothing*): Nejl[Identifier] =
    value

  def unapplySeq(xs: Nejl[Identifier]): Option[Seq[Nothing]] =
    if (xs == value) {
      Some(Seq.empty)
    } else {
      None
    }
}
