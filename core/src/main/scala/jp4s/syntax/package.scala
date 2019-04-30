package jp4s

package object syntax {
  object identifier extends IdentifierSyntax
  object variable extends VariableSyntax

  private[jp4s]
  object optional extends OptionalSyntax
}
