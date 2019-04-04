package jp4s.ast

import com.github.javaparser.ast.expr.SimpleName

import scala.language.implicitConversions

package object name {
  type Simple = SimpleName


  implicit def toSimpleStringContext(sc: StringContext): SimpleStringContext =
    new SimpleStringContext(sc)
}
