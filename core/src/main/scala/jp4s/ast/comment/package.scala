package jp4s.ast

import com.github.javaparser.ast.comments
import com.github.javaparser.ast.comments.JavadocComment

package object comment {
  type BlockComment = comments.BlockComment
  type Comment = comments.Comment
  type Javadoc = JavadocComment
  type LineComment = comments.LineComment
}
