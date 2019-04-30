package jp4s.syntax

import jp4s.ast.Identifier

import scala.language.experimental.macros
import scala.reflect.macros.whitebox

trait IdentifierSyntax {
  implicit def toIdStringContext(sc: StringContext): IdStringContext =
    new IdStringContext(sc)
}

class IdStringContext(private val sc: StringContext) extends AnyVal {
  def id: IdInterpolator =
    macro IdMarco.check
}

private
object IdMarco {
  def check(c: whitebox.Context): c.Expr[IdInterpolator] = {
    import c.universe._

    c.prefix.tree match {
      case Apply(_, List(Apply(_, List(literal @ Literal(Constant(s: String)))))) =>
        if (Identifier.isIdentifier(s)) {
          c.Expr(q"""new _root_.jp4s.syntax.IdInterpolator($literal.asInstanceOf[_root_.jp4s.ast.Identifier])""")
        } else {
          c.abort(c.enclosingPosition, s"$s is not an identifier")
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
