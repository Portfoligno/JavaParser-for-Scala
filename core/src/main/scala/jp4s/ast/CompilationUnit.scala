package jp4s.ast

import jp4s.ast.declaration.{Import, Package, TypeDefinition}
import jp4s.ast.module.Module
import nejc4s.base.{JavaList, Optional}

object CompilationUnit {
  import nejc4s.syntax.optional._

  def apply(
    packageDeclaration: Optional[Package],
    imports: JavaList[Import],
    types: JavaList[TypeDefinition],
    module: Optional[Module]
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
    JavaList[TypeDefinition],
    Optional[Module]
  )] =
    Some((
      u.getPackageDeclaration,
      u.getImports,
      u.getTypes,
      u.getModule
    ))
}
