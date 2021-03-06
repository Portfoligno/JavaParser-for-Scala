package jp4s.ast

import jp4s.ast.expression.Annotation
import nejc4s.base.JavaList

package object `type` {
  type ArrayDimensions = JavaList[JavaList[Annotation]]
  type ArrayType = com.github.javaparser.ast.`type`.ArrayType
  type ClassOrInterfaceType = com.github.javaparser.ast.`type`.ClassOrInterfaceType
  type ImplicitType = com.github.javaparser.ast.`type`.UnknownType
  type IntersectionType = com.github.javaparser.ast.`type`.IntersectionType
  type PrimitiveType = com.github.javaparser.ast.`type`.PrimitiveType
  type ReferenceType = com.github.javaparser.ast.`type`.ReferenceType
  type Type = com.github.javaparser.ast.`type`.Type
  type TypeParameter = com.github.javaparser.ast.`type`.TypeParameter
  type UnionType = com.github.javaparser.ast.`type`.UnionType
  type VarType = com.github.javaparser.ast.`type`.VarType
  type VoidType = com.github.javaparser.ast.`type`.VoidType
  type WildcardType = com.github.javaparser.ast.`type`.WildcardType
}
