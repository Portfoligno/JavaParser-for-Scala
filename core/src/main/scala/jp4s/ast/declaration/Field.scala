package jp4s.ast
package declaration

import com.github.javaparser.ast.body.VariableDeclarator
import jp4s.ast.expression.Annotation
import jp4s.utility.JavaList

object Field {
  def apply(
    modifiers: JavaList[Modifier],
    annotations: JavaList[Annotation],
    variables: JavaList[VariableDeclarator]
  ): Field =
    new Field(
      nodeList(modifiers),
      nodeList(annotations),
      nodeList(variables)
    )

  def unapply(f: Field): Some[(
    JavaList[Modifier],
    JavaList[Annotation],
    JavaList[VariableDeclarator]
  )] =
    Some((f.getModifiers, f.getAnnotations, f.getVariables))
}
