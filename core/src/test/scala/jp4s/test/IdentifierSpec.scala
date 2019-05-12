package jp4s
package test

import nejc4s.NonEmptyJavaList
import org.junit.runner.RunWith
import org.scalatest.FreeSpec
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class IdentifierSpec extends FreeSpec {
  import syntax.identifier._

  "syntax.identifier" - {
    "`id` string context should work" in {
      id"abc" === "abc"
    }

    "`ids` string context should work" in {
      ids"a.b.c" === NonEmptyJavaList("a", "b", "c")
    }
  }
}
