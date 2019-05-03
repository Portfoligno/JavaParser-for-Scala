package jp4s.ast
package `type`

import jp4s.ast.expression.Annotation
import nejc4s.base.{Absent, JavaList, Optional, Present}

object WildcardType {
  import nejc4s.syntax.optional._

  def apply(
    typeBound: Optional[Either[ReferenceType, ReferenceType]],
    annotations: JavaList[Annotation]
  ): WildcardType =
    typeBound match {
      case Present(Right(extendedType)) =>
        wildcardType(Present(extendedType), Absent(), annotations)

      case Present(Left(superType)) =>
        wildcardType(Absent(), Present(superType), annotations)

      case Absent() =>
        wildcardType(Absent(), Absent(), annotations)
    }

  def unapply(t: WildcardType): Some[(
    Optional[Either[ReferenceType, ReferenceType]],
    JavaList[Annotation]
  )] =
    Some((
      (t.getSuperType, t.getExtendedType) match {
        case (Absent(), Present(extendedType)) =>
          Present(Right(extendedType))

        case (Present(superType), Absent()) =>
          Present(Left(superType))

        case (Absent(), Absent()) =>
          Absent()

        case r @ _ =>
          throw new IllegalArgumentException(String.valueOf(r))
      },
      t.getAnnotations
    ))


  private
  def wildcardType(
    extendedType: Optional[ReferenceType],
    superType: Optional[ReferenceType],
    annotations: JavaList[Annotation]
  ): WildcardType =
    new WildcardType(
      extendedType.orElseNull,
      superType.orElseNull,
      nodeList(annotations)
    )
}
