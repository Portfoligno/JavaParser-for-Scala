package jp4s.astx.expression

import com.github.javaparser.utils.StringEscapeUtils
import jp4s.ast.expression._

object PrimitiveLiteral {
  case object Boolean extends Type[Boolean, BooleanLiteral] {
    case object True extends Value(true)
    case object False extends Value(false)


    override
    def apply(value: Boolean): BooleanLiteral =
      new BooleanLiteral(value)

    override
    def unapply(l: BooleanLiteral): Some[Boolean] =
      Some(l.getValue)


    object Value {
      def apply(value: Boolean): Value =
        if (value) True else False

      def unapply(v: Value): Some[Boolean] =
        Some(v.value)
    }

    sealed abstract class Value(private val value: Boolean) {
      def apply(): BooleanLiteral =
        new BooleanLiteral(value)

      def unapply(l: BooleanLiteral): Boolean =
        !l.getValue ^ value
    }
  }

  case object Char extends Type[Char, CharLiteral] {
    override
    def apply(value: Char): CharLiteral =
      new CharLiteral(StringEscapeUtils.escapeJava(s"$value"))

    override
    def unapply(l: CharLiteral): Some[Char] =
      Some(l.asChar)
  }

  case object Int extends Type[Int, IntegerLiteral] {
    override
    def apply(value: Int) =
      new IntegerLiteral(s"$value")

    override
    def unapply(x: IntegerLiteral): Some[Int] =
      Some(x.asInt)
  }

  case object Long extends Type[Long, LongLiteral] {
    override
    def apply(value: Long) =
      new LongLiteral(s"${value}L")

    override
    def unapply(x: LongLiteral): Some[Long] =
      Some(x.asLong)
  }

  case object Float extends Type[Float, FloatingPointLiteral] {
    override
    def apply(value: Float) =
      new FloatingPointLiteral(s"${value}f")

    override
    def unapply(a: FloatingPointLiteral): Option[Float] =
      if (a.getValue.last.toLower == 'f') {
        Some(a.asDouble.toFloat)
      } else {
        None
      }
  }

  case object Double extends Type[Double, FloatingPointLiteral] {
    override
    def apply(value: Double) =
      new FloatingPointLiteral(s"$value")

    override
    def unapply(a: FloatingPointLiteral): Option[Double] =
      if (a.getValue.last.toLower != 'f') {
        Some(a.asDouble)
      } else {
        None
      }
  }


  sealed trait Type[Value, Literal] {
    def apply(value: Value): Literal

    def unapply(l: Literal): Option[Value]
  }
}
