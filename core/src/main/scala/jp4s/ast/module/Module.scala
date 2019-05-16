package jp4s.ast
package module

import jp4s.ast.expression.Annotation
import nejc4s.NonEmptyJavaList
import nejc4s.base.JavaList

object Module {
  object Plain {
    def apply(
      annotations: JavaList[Annotation],
      name: NonEmptyJavaList[Identifier],
      directives: JavaList[ModuleDirective]
    ): Module =
      new Module(
        nodeList(annotations),
        nameNode(name),
        false,
        nodeList(directives)
      )

    def unapply(m: Module): Option[(
      JavaList[Annotation],
      NonEmptyJavaList[Identifier],
      JavaList[ModuleDirective]
    )] =
      if (!m.isOpen) {
        Some((
          m.getAnnotations,
          identifiers(m.getName),
          m.getDirectives
        ))
      } else {
        None
      }
  }

  object Open {
    def apply(
      annotations: JavaList[Annotation],
      name: NonEmptyJavaList[Identifier],
      directives: JavaList[ModuleDirective]
    ): Module =
      new Module(
        nodeList(annotations),
        nameNode(name),
        true,
        nodeList(directives)
      )

    def unapply(m: Module): Option[(
      JavaList[Annotation],
      NonEmptyJavaList[Identifier],
      JavaList[ModuleDirective]
    )] =
      if (m.isOpen) {
        Some((
          m.getAnnotations,
          identifiers(m.getName),
          m.getDirectives
        ))
      } else {
        None
      }
  }
}
