package jp4s.extra.resolution.`type`

import com.github.javaparser.ast.`type`.PrimitiveType.Primitive
import com.github.javaparser.resolution.types.{ResolvedPrimitiveType, ResolvedReferenceType}
import jp4s.ast.`type`.PrimitiveType

object Boxed {
  private
  val variances =
    ResolvedPrimitiveType
      .values()
      .map(t =>
        t.getBoxTypeQName -> PrimitiveType.Variance(Primitive.valueOf(t.name()))
      )
      .toMap

  def unapply(t: ResolvedReferenceType): Option[PrimitiveType.Variance] =
    variances.get(t.getQualifiedName)
}
