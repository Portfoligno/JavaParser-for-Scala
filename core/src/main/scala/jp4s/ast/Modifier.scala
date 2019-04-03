package jp4s.ast

import com.github.javaparser.ast.Modifier.Keyword._
import com.github.javaparser.ast.Modifier.{Keyword => KeywordEnum}

object Modifier {
  private[ast]
  class Keyword(enum: KeywordEnum) {
    def apply(): Modifier =
      new com.github.javaparser.ast.Modifier(enum)

    def unapply(modifier: Modifier): Boolean =
      modifier.getKeyword == enum
  }

  object Default extends Keyword(DEFAULT)
  object Public extends Keyword(PUBLIC)
  object Protected extends Keyword(PROTECTED)
  object Private extends Keyword(PRIVATE)
  object Abstract extends Keyword(ABSTRACT)
  object Static extends Keyword(STATIC)
  object Final extends Keyword(FINAL)
  object Transient extends Keyword(TRANSIENT)
  object Volatile extends Keyword(VOLATILE)
  object Synchronized extends Keyword(SYNCHRONIZED)
  object Native extends Keyword(NATIVE)
  object Strictfp extends Keyword(STRICTFP)
  object Transitive extends Keyword(TRANSITIVE)
  object PackagePrivate extends Keyword(PACKAGE_PRIVATE)
}
