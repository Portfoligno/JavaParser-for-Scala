package jp4s.ast

import com.github.javaparser.ast.modules._

package object module {
  type Exports = ModuleExportsDirective
  type Module = ModuleDeclaration
  type ModuleDirective = com.github.javaparser.ast.modules.ModuleDirective
  type Opens = ModuleOpensDirective
  type Provides = ModuleProvidesDirective
  type Requires = ModuleRequiresDirective
  type Uses = ModuleUsesDirective
}
