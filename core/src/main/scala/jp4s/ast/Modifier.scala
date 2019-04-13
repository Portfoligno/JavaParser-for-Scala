package jp4s.ast

import com.github.javaparser.ast.Modifier.Keyword._

object Modifier {
  type Keyword = com.github.javaparser.ast.Modifier.Keyword

  object Default extends Factory(DEFAULT)
  object Public extends Factory(PUBLIC)
  object Protected extends Factory(PROTECTED)
  object Private extends Factory(PRIVATE)
  object Abstract extends Factory(ABSTRACT)
  object Static extends Factory(STATIC)
  object Final extends Factory(FINAL)
  object Transient extends Factory(TRANSIENT)
  object Volatile extends Factory(VOLATILE)
  object Synchronized extends Factory(SYNCHRONIZED)
  object Native extends Factory(NATIVE)
  object Strictfp extends Factory(STRICTFP)
  object Transitive extends Factory(TRANSITIVE)
  object PackagePrivate extends Factory(PACKAGE_PRIVATE)


  def unapply(m: Modifier): Boolean =
    true


  sealed abstract class Factory(val keyword: Keyword) {
    def apply(): Modifier =
      new Modifier(keyword)

    def unapply(modifier: Modifier): Boolean =
      modifier.getKeyword == keyword
  }
}
