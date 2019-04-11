package jp4s.ast.statement

import jp4s.ast.declaration.Class

object DeclareLocal {
  def apply(classDeclaration: Class): DeclareLocal =
    new DeclareLocal(classDeclaration)

  def unapply(c: DeclareLocal): Some[Class] =
    Some(Class.unsafeFromClassOrInterface(c.getClassDeclaration))
}
