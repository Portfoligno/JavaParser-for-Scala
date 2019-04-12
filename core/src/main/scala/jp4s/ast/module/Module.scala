package jp4s.ast
package module

import jp4s.ast.expression.Annotation
import nejc4s.alias.Nejl
import nejc4s.base.JavaList

object Module {
  def unapply(m: Module): Some[(
    JavaList[Annotation],
    Nejl[Identifier],
    JavaList[ModuleDirective]
  )] =
    Some((
      m.getAnnotations,
      identifiers(m.getName),
      m.getDirectives
    ))


  object Plain {
    def apply(
      annotations: JavaList[Annotation],
      name: Nejl[Identifier],
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
      Nejl[Identifier],
      JavaList[ModuleDirective]
    )] =
      if (!m.isOpen) {
        Module.unapply(m)
      } else {
        None
      }
  }

  object Open {
    def apply(
      annotations: JavaList[Annotation],
      name: Nejl[Identifier],
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
      Nejl[Identifier],
      JavaList[ModuleDirective]
    )] =
      if (m.isOpen) {
        Module.unapply(m)
      } else {
        None
      }
  }
}
