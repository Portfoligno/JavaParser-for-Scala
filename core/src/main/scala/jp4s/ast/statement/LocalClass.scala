package jp4s.ast.statement

import jp4s.ast.declaration.ClassOrInterface

object LocalClass {
  def apply(classDeclaration: ClassOrInterface): LocalClass =
    new LocalClass(classDeclaration)

  def unapply(c: LocalClass): Some[ClassOrInterface] =
    Some(c.getClassDeclaration)
}
