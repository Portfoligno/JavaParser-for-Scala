package jp4s.ast.statement

import jp4s.ast.declaration.Class

object LocalClass {
  def apply(classDeclaration: Class): LocalClass =
    new LocalClass(classDeclaration)

  def unapply(c: LocalClass): Some[Class] =
    Some(Class.unsafeFromClassOrInterface(c.getClassDeclaration))
}
