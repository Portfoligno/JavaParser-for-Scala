package jp4s.syntax

import jp4s.ast.Identifier

import scala.language.experimental.macros
import scala.reflect.macros.whitebox

trait IdentifierSyntax {
  implicit def toIdStringContext(sc: StringContext): IdStringContext =
    new IdStringContext(sc)
}

class IdStringContext(private val sc: StringContext) extends AnyVal {
  def id(unused: Nothing*): Identifier =
    macro IdMarco.expand
}

private
object IdMarco {
  def expand(c: whitebox.Context)(unused: c.Expr[Nothing]*): c.Expr[Identifier] = {
    import c.universe._

    c.prefix.tree match {
      case Apply(_, List(Apply(_, List(literal @ Literal(Constant(s: String)))))) =>
        if (Identifier.isIdentifier(s)) {
          c.Expr(q"""$literal.asInstanceOf[_root_.jp4s.ast.Identifier]""")
        } else {
          c.abort(c.enclosingPosition, s"$s is not an identifier")
        }
    }
  }
}
