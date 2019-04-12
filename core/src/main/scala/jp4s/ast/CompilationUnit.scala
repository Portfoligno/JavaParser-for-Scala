package jp4s.ast

import com.github.javaparser.ast.modules.ModuleDeclaration
import jp4s.ast.declaration.{Import, Package, TypeBody}
import nejc4s.base.{JavaList, Optional}

object CompilationUnit {
  import jp4s.syntax.optional._

  def apply(
    packageDeclaration: Optional[Package],
    imports: JavaList[Import],
    types: JavaList[TypeBody],
    module: Optional[ModuleDeclaration]
  ): CompilationUnit =
    new CompilationUnit(
      packageDeclaration.orElseNull,
      nodeList(imports),
      nodeList(types),
      module.orElseNull
    )

  def unapply(u: CompilationUnit): Some[(
    Optional[Package],
    JavaList[Import],
    JavaList[TypeBody],
    Optional[ModuleDeclaration]
  )] =
    Some((
      u.getPackageDeclaration,
      u.getImports,
      u.getTypes,
      u.getModule
    ))
}
