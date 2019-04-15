[![](https://jitpack.io/v/io.github.portfoligno/javaparser-for-scala.svg)](
https://jitpack.io/#io.github.portfoligno/javaparser-for-scala/$VERSION)
# JavaParser for Scala

This library implements extractor objects along with `apply` factory methods
for the [JavaParser](https://github.com/javaparser/javaparser) AST.

For example:

```scala
// package jp4s.ast.statement
type If = IfStmt // com.github.javaparser.ast.stmt.IfStmt

object If {
  def apply(condition: Expression, thenStmt: Statement, elseStmt: Optional[Statement]): If =
    new If(condition, thenStmt, elseStmt.orElseNull)

  def unapply(i: If): Some[(Expression, Statement, Optional[Statement])] =
    Some((i.getCondition, i.getThenStmt, i.getElseStmt))
}
```

Generally, we prefer a shortened name of the factory object
to improve code conciseness for matching with multi-level patterns.
There are suffices like  `Stmt` and `Expr` in the original AST classes
that are dropped to form shorter names.
You may see below for a detailed example.

Type aliases under the same names
are also provided together as 1-to-1 mapping to the original AST types
in a manner to minimize issues in importing factory objects
while it is still necessary to refer to the original AST types.


Project setup
----
Please refer to instructions on [JitPack](https://jitpack.io/#io.github.portfoligno/javaparser-for-scala/$VERSION).

### sbt
```scala
resolvers += "jitpack" at "https://jitpack.io"

libraryDependencies += "io.github.portfoligno" % "javaparser-for-scala" % VERSION
```

### Gradle (Kotlin DSL)
```kotlin
repositories {
  maven(url = "https://jitpack.io")
}
dependencies {
  implementation("io.github.portfoligno:javaparser-for-scala:$VERSION")
}
```


Usage
---

```scala
// Join variable declarations and assignments
compilationUnit.walk {
  case block: Block =>
    val variableTypes = mutable.Map[String, Type]()

    block.getStatements.removeIf {
      // Search for variables initializing as `null`
      case Execute(Variables(
        JavaList(), // No modifiers
        JavaList(), // No annotations
        t, // The variable type
        NonEmptyJavaList(
          // Only a variable is declared in the statement
          Variable(
            JavaList(), // No array brackets on the name side
            lhs, // The variable name
            Present(NullLiteral()) // Initializing as null
          )
        )
      )) =>
        variableTypes += lhs -> t // Store the variable type for later use
        true

      // Search for variable reassignments
      case execute @ Execute(Assign.Plain(Name(lhs), rhs)) =>
        // Turn it into a declaration
        execute.setExpression(Variables(
          JavaList(),
          JavaList(),
          variableTypes(lhs), // Use the stored variable type
          NonEmptyJavaList(
            Variable(
              JavaList(),
              lhs, // Keep the variable name
              Present(rhs) // Keep the value expression
            )
          )
        ))
        false

      // Ignore anything else
      case _ =>
        false
    }

  case _ =>
}
```
