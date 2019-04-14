package jp4s.ast

import com.github.javaparser.ast.Modifier.Keyword._

import scala.collection.mutable

object Modifier {
  type KeywordEnum = com.github.javaparser.ast.Modifier.Keyword

  case object Default extends Keyword(DEFAULT)
  case object Public extends Keyword(PUBLIC)
  case object Protected extends Keyword(PROTECTED)
  case object Private extends Keyword(PRIVATE)
  case object Abstract extends Keyword(ABSTRACT)
  case object Static extends Keyword(STATIC)
  case object Final extends Keyword(FINAL)
  case object Transient extends Keyword(TRANSIENT)
  case object Volatile extends Keyword(VOLATILE)
  case object Synchronized extends Keyword(SYNCHRONIZED)
  case object Native extends Keyword(NATIVE)
  case object Strictfp extends Keyword(STRICTFP)
  case object Transitive extends Keyword(TRANSITIVE)
  case object PackagePrivate extends Keyword(PACKAGE_PRIVATE)


  def apply(keyword: Keyword): Modifier =
    keyword()

  def unapply(m: Modifier): Some[Keyword] =
    Some(Keyword(m.getKeyword))


  private
  val lookup = mutable.Map[KeywordEnum, Keyword]()

  object Keyword {
    // Ensure all cases are initialized
    Default
    Public
    Protected
    Private
    Abstract
    Static
    Final
    Transient
    Volatile
    Synchronized
    Native
    Strictfp
    Transitive
    PackagePrivate

    def apply(enum: KeywordEnum): Keyword =
      lookup(enum)

    def unapply(k: Keyword): Some[KeywordEnum] =
      Some(k.enum)
  }

  sealed abstract class Keyword(private val enum: KeywordEnum) {
    def apply(): Modifier =
      new Modifier(enum)

    def unapply(modifier: Modifier): Boolean =
      modifier.getKeyword == enum
  }
}
